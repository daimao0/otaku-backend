function login() {
    let username = $("input[name='username']").val();
    let password = $("input[name='password']").val();
    let rememberMe = 0;
    // if ($("input[name='remember']").attr("checked")===true){
    //      remember = 1;
    // }
    if ($('#remember-me').prop("checked")) {
        rememberMe=1;
    }
    let str = "{\"username\":\""+username+"\",\"password\":\""+password+"\",\"rememberMe\":"+rememberMe+"}";
    let data = JSON.parse(str);
    $.ajax({
        type: "POST",
        url: "/auth/login",
        data: JSON.stringify(data),
        success: (response) => {
            window.location.href='/';
            alert("success!")
        },
        error: (result) => {
            console.log(result);
            alert("异常")
        }
    });

    $.ajax({
        type: "HEAD",
        url: window.location.href,
        complete: xhr => {
            console.log(xhr.getAllResponseHeaders())
        }
    })
}