function sweet_Alert(success, error,url_success){
    if(success == null && error == null ){

    }
   else if(success){
        Swal.fire({
            icon:"success",
            title:"Thông Báo",
            text:success,
        }).then(function (){
            if(url_success!= null){
                window.location.href =url_success;
            }
        })
    }else{
        Swal.fire({
            icon:"error",
            title:"Thông Báo",
            text:error
        })
    }
}