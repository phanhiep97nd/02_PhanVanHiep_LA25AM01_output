 var isJapan = false;

 function showJapan() {
     document.getElementById('japan1').classList.remove("japan");
     document.getElementById('japan2').classList.remove("japan");
     document.getElementById('japan3').classList.remove("japan");
     document.getElementById('japan4').classList.remove("japan");
     isJapan = true;
 }

 function checkForm() {
     var user = document.form.user.value;
     var group = document.form.group.value;
     var name = document.form.name.value;
     var kana = document.form.kana.value;
     var yearofbirth = document.form.yearofbirth.value;
     var monthofbirth = document.form.monthofbirth.value;
     var dayofbirth = document.form.dayofbirth.value;
     var email = document.form.email.value;
     var phonenumber = document.form.phonenumber.value;
     var password = document.form.password.value;
     var confirmpass = document.form.confirmpass.value;
     var contract = document.form.contract.value;
     var yearofjob = document.form.yearofjob.value;
     var startyear = document.form.startyear.value;
     var startmonth = document.form.startmonth.value;
     var startday = document.form.startday.value;
     var endyear = document.form.endyear.value;
     var endmonth = document.form.endmonth.value;
     var endday = document.form.endday.value;
     var score = document.form.score.value;
     var level = document.form.level.value;
     var validate = "<h3>Error!</h3>";
     var flag = true;
     if (user == "") {
         validate += '<h4>Hãy nhập アカウント名!!!!!!</h4>';
         console.log('abc');
         flag = false;
     }
     if (group == "") {
         validate += '<h4>Hãy chọn グループ!!!!!!</h4>';
         flag = false;
     }
     if (name == "") {
         validate += '<h4>Hãy nhập 氏名!!!!!!</h4>';
         flag = false;
     }
     if (kana == "") {
         validate += '<h4>Hãy nhập カタカナ氏名!!!!!!</h4>';
         flag = false;
     }
     if (yearofbirth == "" || monthofbirth == "" || dayofbirth == "") {
         validate += '<h4>Hãy chọn 生年月日!!!!!!</h4>';
         flag = false;
     }
     if (email == "") {
         validate += '<h4>Hãy nhập メールアドレス!!!!!!</h4>';
         flag = false;
     }
     if (phonenumber == "") {
         validate += '<h4>Hãy nhập 電話番号!!!!!!</h4>';
         flag = false;
     }
     if (password == "") {
         validate += '<h4>Hãy chọn パスワード!!!!!!</h4>';
         flag = false;
     }
     if (confirmpass == "") {
         validate += '<h4>Hãy nhập パスワード（確認）!!!!!!</h4>';
         flag = false;
     }
     if (contract == "") {
         validate += '<h4>Hãy chọn 雇用形態!!!!!!</h4>';
         flag = false;
     }
     if (yearofjob == "") {
         validate += '<h4>Hãy nhập 職務年数!!!!!!</h4>';
         flag = false;
     }
     if (level == "" && isJapan) {
         validate += '<h4>Hãy chọn 資格!!!!!!</h4>';
         flag = false;
     }
     if ((startyear == "" || startmonth == "" || startday == "") && isJapan) {
         validate += '<h4>Hãy chọn 生年月日!!!!!!</h4>';
         flag = false;
     }
     if ((endyear == "" || endmonth == "" || endday == "") && isJapan) {
         validate += '<h4>Hãy chọn 失効日!!!!!!</h4>';
         flag = false;
     }
     if (score == "" && isJapan) {
         validate += '<h4>Hãy nhập 点数!!!!!!</h4>';
         console.log('abc');
         flag = false;
     }
     document.getElementById('validate').innerHTML = validate;
     return flag;
 }
 var days = ['01',
     '02', '03', '04', '05', '06', '07', '08', '09', 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
 ]
 var month = document.getElementById('month');
 month.onchange = function() {
     var result = '';

     function getDay(j) {
         for (var i = 0; i < j; i++) {
             result += '<option value="' + days[i] + '">' + days[i] + '</option>'
         }
     }
     var choose = month.value;
     switch (choose) {
         case '01':
         case '03':
         case '05':
         case '07':
         case '08':
         case '10':
         case '12':
             getDay(31);
             break;
         case '02':
             getDay(28);
             break;
         default:
             getDay(30);
             break;
     }
     document.getElementById('day').innerHTML = result;
     console.log('abc');
 }

 var startmonth = document.getElementById('startmonth');
 startmonth.onchange = function() {
     var result = '';

     function getDay(j) {
         for (var i = 0; i < j; i++) {
             result += '<option value="' + days[i] + '">' + days[i] + '</option>'
         }
     }
     var choose = startmonth.value;
     switch (choose) {
         case '01':
         case '03':
         case '05':
         case '07':
         case '08':
         case '10':
         case '12':
             getDay(31);
             break;
         case '02':
             getDay(28);
             break;
         default:
             getDay(30);
             break;
     }
     document.getElementById('startday').innerHTML = result;
 }
 var endmonth = document.getElementById('endmonth');
 endmonth.onchange = function() {
     var result = '';

     function getDay(j) {
         for (var i = 0; i < j; i++) {
             result += '<option value="' + days[i] + '">' + days[i] + '</option>'
         }
     }
     var choose = endmonth.value;
     switch (choose) {
         case '01':
         case '03':
         case '05':
         case '07':
         case '08':
         case '10':
         case '12':
             getDay(31);
             break;
         case '02':
             getDay(28);
             break;
         default:
             getDay(30);
             break;
     }
     document.getElementById('endday').innerHTML = result;
 }