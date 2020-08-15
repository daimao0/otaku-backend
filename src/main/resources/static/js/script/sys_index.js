$(()=>{
    $.ajax({
        url:"/sys/manager/main",
        method:"get",
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success:response=>{
            $("#sys-main").html($(response))
        }
    })
})

