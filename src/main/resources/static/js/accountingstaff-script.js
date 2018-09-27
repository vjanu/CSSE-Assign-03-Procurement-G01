/**
 * created by viraj
 **/


/* * * * *     Global Variables     * * * * */
let BASE_URL_LOCAL = 'http://localhost:9000';
let USER_INFO = 'user-info';
let CURRENT_URL = window.location.href;


/* * * * *     Event Triggers     * * * * */
$('#btn-logout').on('click', function (e) {
    e.preventDefault();
    localStorage.removeItem(USER_INFO);
    window.location.href = "index.html";
});

/* * * * *     Headers for cross origin issues   * * * * */
let headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
};


/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('accountingstaff-dashboard')) {
    console.log("You are on accountingstaff-dashboard page")
    
}

if (CURRENT_URL.includes('onhold-payments')) {
    console.log("You are on onhold-payments")
}

if (CURRENT_URL.includes('approved-requests')) {
    console.log("You are on approved-requests page")
}

if (CURRENT_URL.includes('pending-payments')) {
    console.log("You are on pending-payments page");
    loadPurchasedOrders();
}

if (CURRENT_URL.includes('successfull-payments')) {
    console.log("You are on successfull-payments page")
}

if (CURRENT_URL.includes('view-employee-acc')) {
    console.log("You are on view-employee-acc page")
   
}

if (CURRENT_URL.includes('view-supplier-acc')) {
    console.log("You are on view-supplier-acc page")
   
}


/***********  View Purchased Orders Starts ******************/
function loadPurchasedOrders(){
    axios.get(BASE_URL_LOCAL + '/blabla/')//todo
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {
            var html = '<tr>';
            html +='<td align="right">'+request.orderId+'</td>' ;
            html +='<td align="center">' + getItemList(request.items) + '</td>' ;
            html +='<td align="right">'+request.orderDate+'</td>' ;
            html +='<td align="right">'+request.returnedDate+'</td>' ;
            html +='<td align="right">' + getOrderStatusLabels(request.orderStatus) + '</td>';
            html +='<td align="right">' + getOrderHoldLabels(request.orderStatus) + '</td>';
             html +='<td align="right">' +
           '<a href="#" title="" class="btn btn-primary btn-sm" onclick="makeOrderHold(this)">\n' +
           '        <span class="fa fa-hourglass" aria-hidden="true"></span>\n' +
           '        <span><strong>Hold</strong></span></a>'+
            '</a>' +
           '</td>' ;
             html +='<td align="right">' +
            '<a href="#" title="" class="btn btn-danger btn-sm" onclick="payOrder(this)">\n' +
           '        <span class="fa fa-hourglass-end" aria-hidden="true"></span>\n' +
           '        <span><strong>Pay Now</strong></span></a>'+
            '</a>' +
           '</td>' ;
            html +='</tr>';
           $('#view-requests tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

function getOrderStatusLabels(status){
    var badgeClass ='';
    var badgeText='';
    if(status == 1){
        badgeClass = "badge badge-success";
        badgeText = "Complete";
    }
    else if(status == 2){
        badgeClass = "badge badge-warning";
        badgeText = "Partial";
    }
    else{
        badgeClass = "badge badge-secondary";
        badgeText = "Pending";
    }

	var html = '<h4><span class="'+badgeClass+'">'+badgeText+'</span></h4>';
	return html;
}

function getOrderHoldLabels(status){
    var badgeClass ='';
    var badgeText='';
    if(status == 1){
        badgeClass = "badge badge-secondary";
        badgeText = "Hold";
    }
   
    else{
        badgeClass = "badge badge-success";
        badgeText = "Accepted";
    }

	var html = '<h4><span class="'+badgeClass+'">'+badgeText+'</span></h4>';
	return html;
}
window.makeOrderHold = function(ele) {
    var row = $(ele).closest('tr');
    let orderPurchased = {
        orderId: row.find('td:first').text(),
        orderDate: row.find('td:nth-child(2)').text(),   
        returnedDate:  row.find('td:nth-child(3)').text(),   
        orderStatus: row.find('td:nth-child(4)').text(),    
        onHold:1
    }
    //change
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + orderPurchased.orderId, orderPurchased, {
        headers: headers
    })
        .then(response => {
            console.log(response.form3Data);
                   $.notify("Order Marked as on Hold", "warn");
                   
        })
        .catch(error => {
            console.log(error);
                    $.notify("Order Marking Error", "error");           
        })



}


window.payOrder = function(ele) {
    var row = $(ele).closest('tr');
    let orderPurchased = {
        orderId: row.find('td:first').text(),
        orderDate: row.find('td:nth-child(2)').text(),   
        returnedDate: $('#returnedDate').val(),  
        orderStatus: 2,
        
    }
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + orderPurchased.orderId, orderPurchased, {
        headers: headers
    })
        .then(response => {
            console.log(response.form3Data);
                   $.notify("Order Marked as Partially Delivered", "success");
                   
        })
        .catch(error => {
            console.log(error);
            $.notify("Order Marking Error", "error");  
        })



}
/***********  View Purchased Orders Ends ******************/


function payNow(){
    $.notify("Paid Successfully", "success");
}

function clearPayments(){
        
           $('#order-id').val(''),
           $('#supplier-id').val(''),
           $('#supplier-name').val(''),
           $("bank-acc-id").val(''),
           $("bank-acc-name").val(''),
           $('#amount').val('')
           
       
   
}


