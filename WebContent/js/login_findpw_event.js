const login_button = document.getElementById('login_button');

const login_box = document.querySelector('.login_box');

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

