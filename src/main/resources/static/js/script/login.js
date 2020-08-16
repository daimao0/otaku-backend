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
        url: "/login",
        data: JSON.stringify(data),
        dataType: "json",
        contentType : "application/json;charset=utf8",
        success: (response) => {
            console.log(response);
            alert("success!")
        },
        error: (result) => {
            console.log(result);
            alert("异常")
        },
        complete: xhr => {
            let token = xhr.getResponseHeader("Authorization");
            window.localStorage.setItem('Authorization', token);
            console.log(window.localStorage.getItem('Authorization'))
        }
    });


}