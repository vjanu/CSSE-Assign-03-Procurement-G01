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
    loadOnHoldPurchasedOrders();
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
    loadSuccessfullPurchasedOrders();
}

if (CURRENT_URL.includes('view-employee-acc')) {
    console.log("You are on view-employee-acc page")
   
}

if (CURRENT_URL.includes('view-supplier-acc')) {
    console.log("You are on view-supplier-acc page")
   
}

if (CURRENT_URL.includes('pay-for-pending-payments')) {
    populatePaymentDetails();
   
}


/***********  View Purchased Orders Starts ******************/
function loadPurchasedOrders(){
    axios.get(BASE_URL_LOCAL + '/order/all')
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {
            var html = '<tr>';
            html +='<td align="center">'+request.orderId+'</td>' ;
            html +='<td align="center">'+formatDate(request.orderDate)+'</td>' ;
            html +='<td align="center">'+formatDate(request.returnedDate)+'</td>' ;
            html +='<td align="center">'+request.orderId+'</td>' ; //todo
            html +='<td align="center">' + getOrderStatusLabels(request.orderStatus) + '</td>';
            html +='<td align="center">' + getOrderHoldLabels(request.onHold) + '</td>';
             html +='<td align="center">' +
           '<a href="#" title="" class="btn btn-primary btn-sm" onclick="makeOrderHold(this)">\n' +
           '        <span class="fa fa-ban" aria-hidden="true"></span>\n' +
           '        <span><strong>Hold</strong></span></a>'+
            '</a>' +
           '</td>' ;
             html +='<td align="center">' +
            '<a href="pay-for-pending-payments.html#'+request.supplierId+'" title="" class="btn btn-danger btn-sm">\n' +
           '        <span class="fa fa-money" aria-hidden="true"></span>\n' +
           '        <span><strong>Pay Now</strong></span></a>'+
            '</a>' +
           '</td>' ;
            html +='</tr>';
           $('#view-pending-orders-acc tbody').append(html);
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
    if(status == "Complete"){
        badgeClass = "badge badge-success";
        badgeText = "Complete";
    }
    else if(status == "Partial"){
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
    if(status == true){
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
        onHold: true
    }
    //change
        axios.put(BASE_URL_LOCAL + '/order/' + orderPurchased.orderId, orderPurchased, {
        headers: headers
    })
        .then(response => {
            console.log(response.orderPurchased);
                   $.notify("Order Marked as on Hold", "warn");
                   
        })
        .catch(error => {
            console.log(error);
                    $.notify("Order Marking Error", "error");           
        })



}


/***********  View Purchased Orders Ends ******************/


// function payNow(){
//     $.notify("Paid Successfully", "success");
// }

function populatePaymentDetails(){
    if (CURRENT_URL.includes('#')) {
		let supplierId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
		console.log(supplierId);
		$("#supplier-id").val(supplierId);


		axios.get(BASE_URL_LOCAL + '/supplier/'+supplierId).then(function (response) {
			if (response.data) {
				console.log(response);
				// $('#order-id').val(response.data.siteName);//orderid
				$('#supplier-name').val(response.data.supplierName);
				$('#bank-acc-id').val(response.data.bankAccountNo);
				$('#bank-acc-name').val(response.data.bankName);
				// $('#amount').val(response.data.siteName)
			}
		}).catch(function (error) {
			console.log(error);
		});


	}
}

function clearPayments(){
        
           $('#order-id').val(''),
           $('#supplier-id').val(''),
           $('#supplier-name').val(''),
           $("bank-acc-id").val(''),
           $("bank-acc-name").val(''),
           $('#amount').val('')
           
       
   
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}


function loadOnHoldPurchasedOrders(){
    axios.get(BASE_URL_LOCAL + '/order/onhold/true')
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {
            var html = '<tr>';
            html +='<td align="center">'+request.orderId+'</td>' ;
           
            html +='<td align="center">'+formatDate(request.orderDate)+'</td>' ;
            html +='<td align="center">'+formatDate(request.returnedDate)+'</td>' ;
            html +='<td align="center">' + getOrderStatusLabels(request.orderStatus) + '</td>';
            html +='<td align="center">' + getOrderHoldLabels(request.onHold) + '</td>';
           
            html +='</tr>';
           $('#view-onhold-orders-acc tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

function loadSuccessfullPurchasedOrders(){
    axios.get(BASE_URL_LOCAL + '/order/onhold/false')
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {
            var html = '<tr>';
            html +='<td align="center">'+request.orderId+'</td>' ;
           
            html +='<td align="center">'+formatDate(request.orderDate)+'</td>' ;
            html +='<td align="center">'+formatDate(request.returnedDate)+'</td>' ;
            html +='<td align="center">' + getOrderStatusLabels(request.orderStatus) + '</td>';
            html +='<td align="center">' + getOrderHoldLabels(request.onHold) + '</td>';
           
            html +='</tr>';
           $('#view-successfull-orders-acc tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}