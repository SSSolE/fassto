let error_message = document.querySelectorAll('.error_message');
let not_null = document.querySelectorAll('.not_null');
let all_delete = document.querySelectorAll('.all_delete');
let delete_text = document.querySelectorAll('.delete_text');

let input_st = document.querySelectorAll('.input_st');


let join_btn = document.querySelector('.join_btn');

let all_check_box = document.querySelector('.all_check_box');
let eo_check_box = document.querySelectorAll('.eo_check_box');


//function join_check() {
//    if ((email_check == 1) && (pw_check == 1) && (name_check == 1) && (eo_check_box[0].checked == true) && (eo_check_box[1].checked == true)){
//       join_btn.disabled = false;
//        join_btn.style.backgroundColor = "#006C4A";
//        join_btn.style.cursor = "pointer";
//    } else {
//        join_btn.disabled = 'disabled';
//        join_btn.style.backgroundColor = "#CCCCCC";
//       join_btn.style.cursor = "not-allowed";
//    }
//}



// input값 
// 이메일 input
input_st[0].onkeyup = () => {
    if (input_st[0].value.length == 0) {
        error_message[0].style.display = "none";
        not_null[0].style.display = "block";
        delete_text[0].style.display = "none";
        input_st[0].style.border = "1px solid #CCCCCC";
    }else {
        error_message[0].style.display = "none";
        not_null[0].style.display = "none";
        delete_text[0].style.display = "block";
        all_delete[0].style.color = "black";
        input_st[0].style.borderBottom = "none";
        input_st[0].style.color = "black";
    }
}

input_st[0].onblur = () => {
    if (input_st[0].value.length == 0) {
        error_message[0].style.display = "none";
        not_null[0].style.display = "block";
        delete_text[0].style.display = "none";
        input_st[0].style.border = "1px solid #CCCCCC";
        input_st[0].style.color = "black";
    }else if (input_st[0].value.indexOf('@') == -1) {
        error_message[0].style.display = "block";
        not_null[0].style.display = "none";
        delete_text[0].style.display = "block";
        all_delete[0].style.color = "#DC3545";
        input_st[0].style.border = "1px solid #CCCCCC";
        input_st[0].style.color = "#DC3545";
    } else if (input_st[0].value.indexOf('.') == -1) {
        error_message[0].style.display = "block";
        not_null[0].style.display = "none";
        delete_text[0].style.display = "block";
        all_delete[0].style.color = "#DC3545";
        input_st[0].style.border = "1px solid #CCCCCC";
        input_st[0].style.color = "#DC3545";
    }else {
        error_message[0].style.display = "none";
        not_null[0].style.display = "none";
        all_delete[0].style.color = "black";
        input_st[0].style.borderBottom = "none";
        input_st[0].style.color = "black";
    }
}

// 이메일 input

// 비밀번호 input
function pw_fun() {
    if (input_st[1].value.length == 0) {
        error_message[1].style.display = "none";
        not_null[1].style.display = "block";
        delete_text[1].style.display = "none";
        input_st[1].style.border = "1px solid #CCCCCC";
    }else if (input_st[1].value.length < 8) {
        error_message[1].style.display = "block";
        not_null[1].style.display = "none";
        delete_text[1].style.display = "block";
        all_delete[1].style.color = "#DC3545";
        input_st[1].style.border = "1px solid #CCCCCC";
    }else {
        error_message[1].style.display = "none";
        not_null[1].style.display = "none";
        delete_text[1].style.display = "block";
        all_delete[1].style.color = "black";
        input_st[1].style.borderBottom = "none";
    }
}

input_st[1].onkeyup = () => {
    pw_fun() 
}

input_st[1].onblur = () => {
    pw_fun();
    
}
// 비밀번호 input



//비밀번호 확인 input
function pw_check_fun() {
    error_message[2].style.display = "none";
    not_null[2].style.display = "block";
    delete_text[2].style.display = "none";
    input_st[2].style.border = "1px solid #CCCCCC";
}

input_st[2].onkeyup = () => {
    if (input_st[2].value.length == 0) {
        pw_check_fun();
    } else {
        error_message[2].style.display = "none";
        not_null[2].style.display = "none";
        delete_text[2].style.display = "block";
        input_st[2].style.borderBottom = "none";
        input_st[2].style.color = "black";
        all_delete[2].style.color = "black";
    }
}

input_st[2].onblur = () => {
    if (input_st[2].value.length == 0) {
        pw_check_fun();
    } else if ( input_st[2].value == input_st[1].value ) {
        error_message[2].style.display = "none";
        not_null[2].style.display = "none";
        delete_text[2].style.display = "block";
        all_delete[2].style.color = "black";
        input_st[2].style.borderBottom = "none";
        input_st[2].style.color = "black";
    }else {
        error_message[2].style.display = "block";
        not_null[2].style.display = "none";
        delete_text[2].style.display = "block";
        all_delete[2].style.color = "#DC3545";
        input_st[2].style.color = "#DC3545";
        input_st[2].style.border = "1px solid #CCCCCC";
    }
}
//비밀번호 확인 input

//이름 input
function name_fun(check) {
    if (input_st[3].value.length == 0) {
        error_message[3].style.display = "none";
        not_null[3].style.display = "block";
        delete_text[3].style.display = "none";
    } else if( input_st[3].value.length < 2) {
        error_message[3].style.display = "block";
        not_null[3].style.display = "none";
        delete_text[3].style.display = "block";
    } else {
        error_message[3].style.display = "none";
        not_null[3].style.display = "none";
        delete_text[3].style.display = "block";
    }
}

input_st[3].onkeyup = () => {
    name_fun();
}

input_st[3].onblur = () => {
    name_fun()
}

let onlyKorean = function () {
    let pattern = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
    this.value = this.value.replace(pattern, '');
  };
input_st[3].addEventListener('keyup', onlyKorean);
  
//이름 input
  for (let i = 0; i < all_delete.length; i++){
    all_delete[i].onclick = () => {
        input_st[i].value = null;
        error_message[i].style.display = "none";
        not_null[i].style.display = "none";
        all_delete[i].style.color = "black";
        input_st[i].style.borderBottom = "none";
        input_st[3].style.border = "1px solid #CCCCCC";
        input_st[i].style.color = "black";
        delete_text[i].style.display = "none";
        if (i = 0) {
            email_check = 0;
        } else if (i = 2) {
            pw_check = 0;
        } else if(i = 3){
            name_check = 0;
        }
    }
}

// input값 마무리

//checkbox 값

all_check_box.addEventListener('change', (e) => {
    for (let i = 0; i < eo_check_box.length; i++) {
        eo_check_box[i].checked = e.target.checked;
        join_check();
        eo_check_box[i].addEventListener('change', () => {
            if (eo_check_box[0].checked && eo_check_box[1].checked && eo_check_box[2].checked) {
                all_check_box.checked = true;
            } else {
                all_check_box.checked = false;
            }
        });
    }
});

//checkbox 값 마무리

/*join_btn.onclick = () => {
    alert('회원가입이 완료되었습니다.');
}*/


