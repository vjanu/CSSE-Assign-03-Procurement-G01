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
    getSuppliers();
}

if (CURRENT_URL.includes('pay-for-pending-payments')) {
    generateSupplierSelectDropdown()
    populatePaymentDetails();
   
}

if (CURRENT_URL.includes('total-payments')) {
    console.log("You are on total-payments")
    loadAllPayments()
}

if (CURRENT_URL.includes('view-constructors-acc')) {
    console.log("You are on view-constructors-acc page")
    getConstructors()
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
            '<a href="pay-for-pending-payments.html#'+request.orderId+'" title="" class="btn btn-danger btn-sm">\n' +
           '        <span class="fa fa-credit-card" aria-hidden="true"></span>\n' +
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
    if(status == "true"){
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
        orderStatus: row.find('td:nth-child(5)').text(),    
        onHold: "true"
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



function populatePaymentDetails(){
    if (CURRENT_URL.includes('#')) {
		let orderId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
		console.log(orderId);
		$("#order-id").val(orderId);

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

$(document).ready(function() {   

    $(document).on("change", "#item-select", function() {
  
        let data = {
        supplierId : $('#item-select').find(":selected").text()
        }
    
         console.log(data.supplierId);
         axios.get(BASE_URL_LOCAL + '/supplier/'+data.supplierId).then(function (response) {
			if (response.data) {
				console.log(response);
				$('#supplier-name').val(response.data.supplierName);
				$('#bank-acc-id').val(response.data.bankAccountNo);
				$('#bank-acc-name').val(response.data.bankName);
				$('#supplier-email').val(response.data.email);
			}
		}).catch(function (error) {
			console.log(error);
		});
    })
    });

function generateSupplierSelectDropdown() {
	axios.get(BASE_URL_LOCAL + '/supplier/')
		.then(function (response) {
			console.log(response.data)
			var html = '<select class="form-control" id="op-item-select">';

			response.data.forEach(supplier => {
				html += '<option value="' + supplier.supplierId + '">' + supplier.supplierId + '</option>';
			});


            html += '</select>';
            console.log(html)
			$('#item-select').append(html);
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
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
    axios.get(BASE_URL_LOCAL + '/order/onhold/'+true)
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
    axios.get(BASE_URL_LOCAL + '/order/onhold/'+false)
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

/***********  View Suppliers Start ******************/

function getSuppliers() {
    axios.get(BASE_URL_LOCAL + '/supplier/')
    .then(response => {
        if (response.status == 200) {
            console.log(response.data);
            $('#view-supp-acc').append(getSupplierTable('supplier-table', response.data));
            window.$('#supplier-table').DataTable();
        }
    })
    .catch(err => {
        console.log(err);
    });
}



function getSupplierTable(tableId, suppliers) {
        let html =
            '<table class="table table-striped table-bordered" id="'+ tableId +'">' +
            '<thead>' +
            '<tr>' +
            '<th align="center"scope="col">Supplier ID</th>' +
            '<th align="center" scope="col">Supplier Name</th>' +
            '<th align="center" scope="col">Bank Account No</th>' +
            '<th align="center" scope="col">Bank Name</th>' +
            '<th align="center" scope="col">Address</th>' +
            '<th align="center" scope="col">Email</th>' +
            '<th align="center" scope="col">Phone</th>' +
            '<th align="center" scope="col">Availability</th>' +
            '</tr>' +
            '</thead>' +
            '<tbody>';  
    
        
        suppliers.forEach(request => {
            html +=
                '<tr>'+
                    '<td align="center">' + request.supplierId + '</td>' +
                    '<td align="center">' + request.supplierName + '</td>' +
                    '<td align="center">' + request.bankAccountNo + '</td>' +
                    '<td align="center">' + request.bankName  + '</td>' +
                    '<td align="center">' + request.address  + '</td>' +
                    '<td align="center">' + request.email  + '</td>' +
                    '<td align="center">' + request.phone  + '</td>' +
                    '<td align="center">' + getBannedLabel(request.isBanned) + '</td>' +
                    
                '</tr>';
        });
    
        html += '</tbody></table>'; 
    
        return html;
    }

    

function getBannedLabel(status){
    var badgeClass ='';
    var badgeText='';
    if(status == true){
        badgeClass = "badge badge-danger";
        badgeText = "Banned";
    }
   
    else{
        badgeClass = "badge badge-success";
        badgeText = "Unbanned";
    }

	var html = '<h4><span class="'+badgeClass+'">'+badgeText+'</span></h4>';
	return html;
}
/***********  View Suppliers Ends ******************/


function pay(){
    let data='';
	 data = {
		paymentID : 0,
		paymentMethod : $('#method').find(":selected").text(),
        supplierId : $('#item-select').find(":selected").text(),
        totAmount : $("#amount").val(),
        orderID : $("#order-id").val(),
        bankNo : $("#bank-acc-id").val(),
        date : $("#date").val(),
        email : $("#supplier-email").val()
		
		
    }

    axios.post(BASE_URL_LOCAL + '/email', data)
    .then(function (response) {
    	
    })
    .catch(function (error) {
       
    });

	axios.post(BASE_URL_LOCAL + '/payment', data)
    .then(function (response) {
    	$.notify("Successfully Paid", "success");
    })
    .catch(function (error) {
        // handle error
        $.notify("Payment Failed", "error");
    });


}

function sentEmail(){
    let data='';
	 data = {
		
 
        totAmount : $("#amount").val(),
        orderID : $("#order-id").val(),
        bankNo : $("#bank-acc-id").val(),
        date : $("#date").val(),
        email : $("#supplier-email").val()
		
		
    }

    axios.post(BASE_URL_LOCAL + '/email', data)
    .then(function (response) {
    	$.notify("Receipt Sent to "+ data.email, "success");
    })
    .catch(function (error) {
        $.notify("Email is not sent", "error");
    });

	


}


function loadAllPayments(){
    axios.get(BASE_URL_LOCAL + '/payment')
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {


            var html = '<tr>';
            html +='<td align="center">'+request.paymentID+'</td>' ;
            html +='<td align="center">' + request.orderID +'</td>' ;
            html +='<td align="center">' + request.supplierId + '</td>' ;
            html +='<td align="center">' + request.date + '</td>' ;
            html +='<td align="center">' + request.totAmount + '</td>' ;
            html +='<td align="center">' + request.bankNo + '</td>';
            html +='<td align="center">' + request.paymentMethod + '</td>' ;
            
            html +='</tr>';
           $('#view-tot-acc tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}



/***********  View Constructor Start ******************/

function getConstructors() {
    axios.get(BASE_URL_LOCAL + '/employee/constructor/')
    .then(response => {
        if (response.status == 200) {
            console.log(response.data);
            $('#view-con-acc').append(getConstructorTable('constructor-table', response.data));
            window.$('#constructor-table').DataTable();
        }
    })
    .catch(err => {
        console.log(err);
    });
}



function getConstructorTable(tableId, constructors) {
        let html =
            '<table class="table table-striped table-bordered" id="'+ tableId +'">' +
            '<thead>' +
            '<tr>' +
            '<th align="center"scope="col">Constructor ID</th>' +
            '<th align="center" scope="col">Constructor Name</th>' +
            '<th align="center" scope="col">Site ID</th>' +
            '<th align="center" scope="col">Address</th>' +
            '<th align="center" scope="col">Email</th>' +
            '<th align="center" scope="col">Phone</th>' +
             '<th align="center" scope="col">Availability Status</th>' +
         
            '</tr>' +
            '</thead>' +
            '<tbody>';  
    
        
            constructors.forEach(request => {
            html +=
                '<tr>'+
                    '<td align="center">' + request.employeeId + '</td>' +
                    '<td align="center">' + request.employeeName + '</td>' +
                    '<td align="center">' + request.site + '</td>' +
                    '<td align="center">' + request.address  + '</td>' +
                  
                    '<td align="center">' + request.email  + '</td>' +
                    '<td align="center">' + request.phone  + '</td>' +
                    '<td align="center">' + getBannedLabel(request.isBanned) + '</td>' +
                    
                '</tr>';
        });
    
        html += '</tbody></table>'; 
    
        return html;
    }



/***********  View Constructor Ends ******************/