/** 判断传入对象是否为空 **/
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == "" || obj == "null" || obj == "NULL"){
        return true;
    }else{
        return false;
    }
}