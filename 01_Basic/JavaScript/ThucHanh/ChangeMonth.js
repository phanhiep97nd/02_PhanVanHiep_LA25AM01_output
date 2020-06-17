var days = ['01',
    '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'
]
var month = document.getElementById('month');
month.onchange = function() {
    var result = '';

    function getDay(j) {
        for (var i = 0; i < j; i++) {
            result += '<option value="' + days[i]+'"' +'id="'+days[i]+ '">' + days[i] + '</option>'
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
    document.getElementById('day').onchange = function() {
        document.getElementById('content').innerHTML = 'Ngày: ' + document.getElementById('day').value + ' Tháng: ' +
            month.value + '!';
    }
}
var today = new Date();
function setDay(){
    var select = document.getElementById('month').children;
    for(var i=0; i< select.length; i++){
    if((today.getMonth()+1)==select[i].value)
        document.getElementById(select[i].value).selected = "selected";
    }
        document.getElementById('day').innerHTML= '<option value="' + today.getDate()+'"' +'id="'+today.getDate()+ '">' + today.getDate() + '</option>'
}
setDay();