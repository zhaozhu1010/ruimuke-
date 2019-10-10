function deleteUser(Id) {
    var v=window.confirm("确定删除？");   //交互框 windows对象
    // var userId=$(this).prev().val();
    // alert($(this).val());

    alert("id："+Id);
    if(v==true){
        $.ajax({
            type:"delete",
            url:"deleteUser/"+Id,    //restful风格
            data:{userId:Id},
            dataType:"json",
            success:function (data) {
                alert(data.result);
                window.location.reload();    //删除后刷新！！
            }
        })
    }
}
