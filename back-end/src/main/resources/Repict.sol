pragma solidity ^0.4.24; //check

import "./Table.sol";

contract Repict{
    struct Company{
        string name;
        uint balance;
        address addr;
        uint cType; // 0:financial company like bank, 1:oridinary compnay
        string password;
        string email;
        string phone;
    }
    struct Recipts{
        address from;
        address to;
        uint count;
        uint expiredTime;
        uint status; // 0 - notPaid, 1 - paid
        string [] check;
    }
    
    mapping (string => address) mapSA;
    mapping (address => Company) mapC;
    mapping (address => Recipts[]) mapR;
    event AddRecipt(address from, address to, uint count, uint expiredTime);
    event TransferRecipt(address from, address originFrom, address to, uint count);
    event Finance(address bank, address from,address reciptFrom,uint count);
    event PayBack(address from, address to,uint count);
    event PayFor(address from, address to,uint count);
    event Validate(address bank,address from,address to,uint count);
    event GetRecipt(uint count,uint expiredTime,uint status,string check1);
    
    event SetCompany(address company);
    event GetCompany(string name,uint cType,uint balance,string email,string phone,string password);
    
    
    constructor(){
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        tf.createTable("t_company", "name", "addr,balance,cType,password,email,phone");
        //key: address
        //cType 0:financial company like bank, 1:oridinary compnay
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_company");
        return table;
    }
    
    function setCompany(string _name,uint _balance,uint _cType,string _password,string _phone,string _email) public{
        mapSA[_name] = msg.sender;
        mapC[msg.sender] = Company({
           name: _name,
           balance: _balance,
           addr:msg.sender,
           cType:_cType,
           email:_email,
           phone:_phone,
           password:_password
        });
        emit SetCompany(msg.sender);
    }

    function definance(string _name, address _company, address _reciptFrom, uint _count){
        mapC[mapSA[_name]].balance = 1000;
        mapC[_company].balance = _count;
        mapC[mapSA[_name]].phone = "1123123";
    }

    function updateCompany(string _name,string _phone,string _email) public{
        mapC[mapSA[_name]].phone = _phone;
        mapC[mapSA[_name]].email = _email;
    }

    function getCompanyAddress(string _name) public returns(address){
        return mapSA[_name];
    }
    
    
    function getCompany(address _addr) public returns(string,uint,uint,string,string,string){
        emit GetCompany(mapC[_addr].name,mapC[_addr].cType,mapC[_addr].balance,mapC[_addr].email,mapC[_addr].phone,mapC[_addr].password);
        return (mapC[_addr].name,mapC[_addr].cType,mapC[_addr].balance,mapC[_addr].email,mapC[_addr].phone,mapC[_addr].password);
    }

    function getRecipt(address _from, address _to, uint _count) public returns (uint,uint,uint,string){
        uint index = mapR[_from].length;
        for(uint i = 0 ; i < mapR[_from].length ; i ++){
            if(mapR[_from][i].from == _from &&  mapR[_from][i].to == _to &&  mapR[_from][i].count == _count &&  mapR[_from][i].status == 0){
                index = i;
                break;
            }
        }
        emit GetRecipt(mapR[_from][index].count,mapR[_from][index].expiredTime,mapR[_from][index].status,mapR[_from][index].check[1]);
        return (mapR[_from][index].count,mapR[_from][index].expiredTime,mapR[_from][index].status,mapR[_from][index].check[1]);
    }
    
    //both**
    function addRecipt(string _name,address _to,uint _count, uint _expiredTime) public{
        mapR[mapSA[_name]].push(Recipts({
            from:mapSA[_name],
            to:_to,
            count:_count,
            expiredTime:now + _expiredTime,
            status:0,
            check: new string[](1)
        }));
        mapR[_to].push(Recipts({
            from:mapSA[_name],
            to:_to,
            count:_count,
            expiredTime:now + _expiredTime,
            status:0,
            check: new string[](1)
        }));
        emit AddRecipt(mapSA[_name],_to,_count,now+_expiredTime);
    }
    
    function transferRecipt(string _name,address _from,address _to,uint _count) public{
        uint index = mapR[mapSA[_name]].length;
        for(uint i = 0 ; i < mapR[mapSA[_name]].length ; i ++){
            if(mapR[mapSA[_name]][i].from == _from &&  mapR[mapSA[_name]][i].to == mapSA[_name] &&  mapR[mapSA[_name]][i].count >= _count &&  mapR[mapSA[_name]][i].status == 0){
                index = i;
                break;
            }
        }
        for(uint j = 0 ; j < mapR[_from].length ; j ++){
            if(mapR[_from][j].from == _from && mapR[_from][j].to == mapSA[_name] && mapR[_from][j].count == mapR[mapSA[_name]][index].count && mapR[_from][j].status == 0){
                if(mapR[_from][j].count == _count){
                    mapR[_from][j].status = 1;
                }
                mapR[_from][j].count -= _count;
                break;
            }
        }
        mapR[mapSA[_name]][index].count -= _count;
        
        mapR[_from].push(Recipts({
            from:_from,
            to:_to,
            count:_count,
            expiredTime:mapR[mapSA[_name]][index].expiredTime,
            status:0,
            check:new string[](1)
        }));
        mapR[_to].push(Recipts({
            from:_from,
            to:_to,
            count:_count,
            expiredTime:mapR[mapSA[_name]][index].expiredTime,
            status:0,
            check:new string[](1)
        }));
        emit TransferRecipt(mapSA[_name],_from,_to,_count);
    }
    
    function payBack(string _name,address _from){
        uint index = mapR[mapSA[_name]].length;
        for(uint i = 0 ; i < mapR[mapSA[_name]].length ; i ++){
            if(mapR[mapSA[_name]][i].from == _from && mapR[mapSA[_name]][i].to == mapSA[_name] && mapR[mapSA[_name]][i].status == 0){
                index = i;
                break;
            }
        }
        if(now >= mapR[mapSA[_name]][index].expiredTime){
            uint ccount = mapR[mapSA[_name]][index].count;
            mapC[_from].balance -= mapR[mapSA[_name]][index].count;
            mapC[mapSA[_name]].balance += mapR[mapSA[_name]][index].count;
            mapR[mapSA[_name]][index].status = 1;
            for(uint j = 0 ; j < mapR[_from].length ; j ++){
            if(mapR[_from][j].from == _from && mapR[_from][j].to == mapSA[_name] && mapR[_from][j].count == mapR[mapSA[_name]][index].count && mapR[_from][j].status == 0){
                    mapR[_from][j].status = 1;
                    break;
                }
            }
            emit PayBack(_from,mapSA[_name],ccount);
        }
    }
    
    function payFor(string _name,address _to,uint _count){
        uint index = mapR[mapSA[_name]].length;
        for(uint i = 0 ; i < mapR[mapSA[_name]].length ; i ++){
            if(mapR[mapSA[_name]][i].from == mapSA[_name] && mapR[mapSA[_name]][i].to == _to && mapR[mapSA[_name]][i].count >= _count && mapR[mapSA[_name]][i].status == 0){
                index = i;
                break;
            }
        }
        mapC[mapSA[_name]].balance -= mapR[mapSA[_name]][index].count;
        mapC[_to].balance += mapR[mapSA[_name]][index].count;
        mapR[mapSA[_name]][index].status = 1;
        for(uint j = 0 ; j < mapR[_to].length ; j ++){
        if(mapR[_to][j].from == mapSA[_name] && mapR[_to][j].to == _to && mapR[_to][j].count == mapR[mapSA[_name]][index].count && mapR[_to][j].status == 0){
                mapR[_to][j].status = 1;
                break;
            }
        }
        emit PayFor(mapSA[_name],_to,_count);
    }

    function finance(string _name, address _company, address _reciptFrom, uint _count) public{
        uint index = mapR[_company].length;
        for(uint i = 0 ; i <  mapR[_company].length ; i ++){
            if(mapR[_company][i].from == _reciptFrom &&  mapR[_company][i].to == _company &&  mapR[_company][i].count >= _count &&  mapR[_company][i].status == 0){
                index = i;
                break;
            }
        }
        mapC[_company].balance += _count;
        mapC[mapSA[_name]].balance -= _count;
        emit Finance(mapSA[_name],_company,_reciptFrom,_count);
    }
    
    function validate(string _name,address _from, address _to, uint _count) public {
        uint index = mapR[_from].length;
        for(uint i = 0 ; i < mapR[_from].length ; i ++){
            if(mapR[_from][i].from == _from &&  mapR[_from][i].to == _to &&  mapR[_from][i].count == _count &&  mapR[_from][i].status == 0){
                index = i;
                break;
            }
        }
        mapR[_from][index].check.push(mapC[mapSA[_name]].name);
        for(uint j = 0 ; j < mapR[_to].length ; j ++){
            if(mapR[_to][j].from == _from && mapR[_to][j].to == _to && mapR[_to][j].count ==_count && mapR[_to][j].status == 0){
                mapR[_to][j].check.push(mapC[mapSA[_name]].name);
                break;
            }
        }
        emit Validate(mapSA[_name],_from,_to,_count);
    }
}
