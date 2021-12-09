const login_button = document.getElementById('login_button');

const login_box = document.querySelector('.login_box');

let lo_su_btn = document.querySelector('.lo_su_btn');
let fpw_su_btn = document.querySelector('.fpw_su_btn');

login_button.onclick = () => {
    login_box.style.display = 'block';
    let find_pw = document.querySelector('.find_pw');
    let login_container = document.querySelector('.login_container');
    let findPw_contaier = document.querySelector('.findPw_contaier');
    find_pw.onclick = () => {
        login_container.style.display = 'none';
        findPw_contaier.style.display = 'block';
        const fpw_exit_btn = document.querySelector('.fpw_exit_btn');
        fpw_exit_btn.onclick = () => {
            login_box.style.display = 'none';
            login_container.style.display = 'block';
            findPw_contaier.style.display = 'none';
        }
        opacity_st.onclick = () => {
            login_box.style.display = 'none';
            login_container.style.display = 'block';
            findPw_contaier.style.display = 'none';
        }
    }
}

const exit_btn = document.querySelector('.exit_btn');
const opacity_st = document.querySelector('.opacity_st');

exit_btn.onclick = () => {
    login_box.style.display = 'none';
    login_container.style.display = 'block';
    findPw_contaier.style.display = 'none';
}
opacity_st.onclick = () => {
    login_box.style.display = 'none';
}

const dp_pw = document.getElementById('dp_pw');
const dp_none_pw = document.getElementById('dp_none_pw');
let pw_type = document.getElementById('pw_type');


dp_none_pw.onclick = () => {
    dp_pw.style.display = 'block';
    dp_none_pw.style.display = 'none';
    pw_type.type = 'text';

}

dp_pw.onclick = () => {
    dp_none_pw.style.display = 'block';
    dp_pw.style.display = 'none';
    pw_type.type = 'password';
}


function loadJQuery() {
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.charset = "utf-8";		  
    oScript.src = "http://code.jquery.com/jquery-1.6.2.min.js";	
    document.getElementsByTagName("head")[0].appendChild(oScript);
}

loadJQuery();


lo_su_btn.onclick = () => {
    $.ajax({
        url: "/FASSTO/login",
        type: "POST",
        success: function () {
            window.location = './index.html';
        },
        error: function () {
            //alert("존재하지 않는 아이디입니다.");
            alert("비밀번호가 틀렸습니다.");
        }
    });
}


fpw_su_btn.onclick = () => {
    $.ajax({
        //url: "",
        type: "POST",
        success: function () {
            window.location = './index.html';
        },
        error: function () {
            alert("존재하지 않는 이메일입니다.");
            alert("존재하지 않는 이름입니다.")
        }
    });
}
