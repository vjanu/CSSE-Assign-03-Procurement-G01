/**
 * created by viraj
 **/


/* * * * *     Global Variables     * * * * */
let BASE_URL_LOCAL = 'http://localhost:9000';
let USER_INFO = 'user-info';
let CURRENT_URL = window.location.href;
let itemsArray = [];

/* * * * *     Event Triggers     * * * * */
$('#btn-logout').on('click', function (e) {
    e.preventDefault();
    localStorage.removeItem(USER_INFO);
    window.location.href = "index.html";
});


$('#btn-add-request').on('click', function (e) {
    e.preventDefault();
    addRequest();
});
$('#btn-add-item').on('click', function (e) {
	e.preventDefault();
	addRequiredItem();
});



/* * * * *     Headers for cross origin issues   * * * * */
let headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
};


/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('view-requests-sitemanager')) {
    console.log("You are on view-requests-sitemanager page")
    loadRequestsFromConstructor()
    
}

if (CURRENT_URL.includes('sitemanager-dashboard')) {
    console.log("You are on sitemanager-dashboard")
}

if (CURRENT_URL.includes('approved-requests')) {
    console.log("You are on approved-requests page")
    loadRequestsBasedOnStatus();
}

if (CURRENT_URL.includes('pending-requests')) {
    console.log("You are on pending-requests page")
    loadRequestsBasedOnStatus();
}

if (CURRENT_URL.includes('rejected-requests')) {
    console.log("You are on rejected-requests page")
    loadRequestsBasedOnStatus();
}

if (CURRENT_URL.includes('immediate-requests')) {
    console.log("You are on immediate-requests page")
    loadRequestsBasedOnImmediate()
   
}
if (CURRENT_URL.includes('view-orders-sitemanager')) {
    console.log("You are on view-orders-sitemanager page")
    loadPurchasedOrders()
   
}



if (CURRENT_URL.includes('request-order-site-manager')) {
    console.log("You are on request-order-site-manager page")
	generateItemSelectDropdown2();
}


if (CURRENT_URL.includes('make-request')) {
    populateRequestDetails()
   
}

$('#btn-add-item-to-site').on('click', function (e) {
	e.preventDefault();
	addItemToSite1();
});

if (CURRENT_URL.includes('update-stock-sitemanager')) {
	generateItemSelectDropdown1();
	
}


//constructor add requests-add request page



function addRequest() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};
	for (var i of storedItems) {
		obj[i.itemName] = i.itemQty;
	}

	let data = {
			requestId: $('#requestId').val(),
			requestedPerson: $('#constructorname').val(),
		    requestedDate: $('#requesteddate').val(),
		    siteId: $('#siteId').val(),
            items: obj,
            requestingDate: $('#requestingdate').val(),
            isSiteManagerApproved:"0"
		
	}

	axios.post(BASE_URL_LOCAL + '/requestmaterial/add-new-request', data)
		.then(function (response) {
			$.notify("Request Added Successfully", "success");
		})
		.catch(function (error) {
			// handle error
			console.log(error);
			$.notify("Request not added", "error");
		});
}

function addRequiredItem() {
	if ($('#item-qty').val() != "") {

		let data = {
			itemId: $('#op-item-select1').val(),
			itemName: $('#op-item-select1 :selected').text(),
			itemQty: $('#item-qty').val()
		}		
		itemsArray.push(data);
		localStorage.setItem('items', JSON.stringify(itemsArray));
		loadAddedItemTable1();
	} else {
		$.notify("Quantity cannot be empty", "error");
	}

}

function loadAddedItemTable1() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	console.log(storedItems)

	for (var i of storedItems) {
		console.log(i.itemId + " | " + i.itemName);
		var html = '<tr>';
		html += '	<td class="nr-itemId"><center>' + i.itemId + '</center></td>';
		html += '	<td class="text-center">' + i.itemName + '</td>';
		html += '	<td class="text-center">' + i.itemQty + '</td>';
		html += '	<td class="text-center">' +
			'		<a href="#" title="" id ="btn-clear" class="btn btn-danger btn-sm">\n' +
			'        	<span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
			'        	<span><strong>Remove</strong></span></a>' +
			'		</a>' +
			'	</td>';
		html += '</tr>';
	}

	$('#request-item-table1 tbody').append(html);
}

//generate item selection dropdown list in add request page
function generateItemSelectDropdown2() {
	axios.get(BASE_URL_LOCAL + '/item/')
		.then(function (response) {
			console.log(response.data)
			var html = '<select class="form-control" id="op-item-select1">';

			Object.keys(response.data).forEach(supplierAsKey => {
				response.data[supplierAsKey].forEach(item => {
					html += '<option value="' + item.itemId + '">' + item.itemName + '</option>';
				});
			});


			html += '</select>';
			$('#item-select1').append(html);
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}



//click event for remove button when adding an item
$(document).on('click', '#request-item-table .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var itemId = $(this).closest("tr").find(".nr-itemId").text();
	console.log(itemId);
	var r = confirm("Are you sure you want to remove this item ? This action cannot be undone");
	if (r == true) {


		let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];

		var index = storedItems.findIndex(function (item, i) {
			return item.itemId === itemId
		});

		console.log("Index : " + index);
		if (index != -1) {
			storedItems.splice(index, 1);
		}

		localStorage.setItem('items', JSON.stringify(storedItems));
		$("#item-list tbody").empty();

		loadItemTable();


		$.notify(itemId, "success");
	}
});


//click event for remove button when adding an item
$(document).on('click', '#request-item-table .btn-clear', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var itemId = $(this).closest("tr").find(".nr-itemId").text();
	console.log(itemId);
	var r = confirm("Are you sure you want to remove this item ? This action cannot be undone");
	if (r == true) {


		let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];

		var index = storedItems.findIndex(function (item, i) {
			return item.itemId === itemId
		});

		console.log("Index : " + index);
		if (index != -1) {
			storedItems.splice(index, 1);
		}

		localStorage.setItem('items', JSON.stringify(storedItems));
		$("#item-list1 tbody").empty();

		loadItemTable1();


		$.notify(itemId, "success");
	}
});


function loadRequestsFromConstructor(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
     .then(function (response) {
    	 console.log(response)
    	 response.data.forEach(request => {


             var html = '<tr>';
             html +='<td align="right">'+request.requestId+'</td>' ;
             html +='<td align="right">' + request.requestedPerson +'</td>' ;
             html +='<td align="right">' + request.requestedDate + '</td>' ;
             html +='<td align="right">' + request.siteId + '</td>' ;
             html +='<td align="right">' + getItemList(request.items) + '</td>' ;
             html +='<td align="right">' + getImmediateLabels(request.isImmediated) + '</td>';
             html +='<td align="right">' + getApprovedLabels(request.isSiteManagerApproved) + '</td>' ;
              html +='<td align="right">' +
            '<a href="make-request.html#'+request.requestId+'"title="" class="btn btn-primary btn-sm">\n' +
            '        <span class="far fa-check-square" aria-hidden="true"></span>\n' +
            '        <span><strong>Make Request</strong></span></a>'+
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


window.getConfirmation = function(ele) {
        var row = $(ele).closest('tr');
        // alert('TR first cell: ' + row.find('td:first').text());
        // alert('TR first cell: ' + row.find('td:nth-child(3)').text());
        let form3Data = {
            requestId: row.find('td:first').text(),
            requestedPerson: row.find('td:nth-child(2)').text(),   
            siteId: row.find('td:nth-child(4)').text(),  
            requestedDate: row.find('td:nth-child(3)').text(),     
            // items: row.find('td:nth-child(5)').text(), 
            isImmediated: row.find('td:nth-child(6)').text(),  
            isSiteManagerApproved: 1
            
        }
            axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + form3Data.requestId, form3Data, {
            headers: headers
        })
            .then(response => {
                console.log(response.form3Data);
                       $.notify("Successfully Approved", "success");
                       
            })
            .catch(error => {
                console.log(error);
                        $.notify("Approval Declined", "error");;           
            })

   

}

// setTimeout(function(){
//     $( "#view-requests" ).load( "view-requests-sitemanager.html #view-requests" );
//  }, 1000); //refresh every 1 second
// show a dialog box when clicking on a link



function getItemList(items){
    var html = '';
    // // var count = 0;
    // console.log(items)
    // // console.log(items.length)
  
    // // console.log(items.data.length)
  
    
    // for(var count in items){
    // axios.get(BASE_URL_LOCAL + '/item/'+count)
    //  .then(response => {
    // 	 console.log(response.data)
    	
    //         var i = response.data.itemName;
    //         console.log("name: "+i)
    //         itemsArray.push(i);
    	 
    //  })
    
    //  .catch(function (error) {
    //      // handle error
    //      console.log(error);
    //  });
    // }
	for (var key in items) {

		if (items.hasOwnProperty(key)) {
			html += key +' : '+items[key]+'<br/>';
		}
	}
	
	return html;
}

function getApprovedLabels(status){
    var btnClass='';
    var btnText = '';
    var isDisabled = '';
    if(status=="1"){
        btnClass = "btn btn-success btn-sm";
        btnText = "Approved";
        isDisabled = "disabled";
    }
    else if(status=="2"){
        btnClass = "btn btn-danger btn-sm";
        btnText = "Declined";
        isDisabled = "disabled";
    }
    else{
        btnClass = "btn btn-secondary btn-sm" ;
        btnText = "Pending";
        isDisabled = "disabled";
    }

if(btnText == "Pending"){
	var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-question" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
            '</button>';
}
else if(btnText == "Approved"){
    var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-check" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
            '</button>';
}
else if(btnText == "Declined"){
    var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-minus" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
            '</button>';
}
	return html;
}

function getImmediateLabels(status){
	var badgeClass = (status == 1) ? "badge badge-pill badge-danger" : "badge badge-pill badge-warning" ;
	var badgeText = (status == 1) ? "Yes" : "No" ;

	var html = '<h4><span class="'+badgeClass+'">'+badgeText+'</span></h4>';
	return html;
}


//*
$(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#view-requests").on('click','tr',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").html(); // get current row 1st table cell TD value
         var col2=currentRow.find("td:eq(1)").html(); // get current row 2nd table cell TD value
         var col3=currentRow.find("td:eq(2)").html(); // get current row 3rd table cell  TD value
         var col33=currentRow.find("td:eq(4)").html(); // get current row 3rd table cell  TD value
         var col31=currentRow.find("td:first").html(); // get current row 3rd table cell  TD value
         var data=col1+"\n"+col2+"\n"+col3;
         $("#orderId").val(col1);
         $("#orderId1").val(col2);
         $("#orderId2").val(col3);
         $("#orderId3").val(col31);
         $("#orderId5").val(col33);
        //  alert(data);
    });
});


//*
$(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#view-requests").on('click','tr',function(){
         // get the current row
         var currentRow=$(this).closest("tr"); 
         
         var col1=currentRow.find("td:eq(0)").html(); // get current row 1st table cell TD value
         var col2=currentRow.find("td:eq(1)").html(); // get current row 2nd table cell TD value
         var col3=currentRow.find("td:eq(2)").html(); // get current row 3rd table cell  TD value
         var col33=currentRow.find("td:eq(4)").html(); // get current row 3rd table cell  TD value
         var col31=currentRow.find("td:first").html(); // get current row 3rd table cell  TD value
         var data=col1+"\n"+col2+"\n"+col3;
         $("#orderId").val(col1);
         $("#orderId1").val(col2);
         $("#orderId2").val(col3);
         $("#orderId3").val(col31);
         $("#orderId5").val(col33);
        //  alert(data);
    });
});



// get the item list when request id is typed

    // $('#request-id').on('input',function(e){
    //     let x='';
    //     let data = {
    //       reqId : $('#request-id').val()
    //     }
    //      console.log(data.reqId);
    //     axios.get(BASE_URL_LOCAL + '/requestmaterial/'+ data.reqId)
    //     .then(function (response) {
    //         console.log(response.data.items)
    //         // console.log(JSON.parse(response));
    //             // $("#items").val(response.data.orderId);
    //             $(function () {
    //                 $.each(response.data.items, function (i, item) {
    //                     console.log(i);
    //                     console.log(item);
    //                      x += i+" : "+item+' ';
    //                      console.log(x);
                       
    //                 });
    //             $("#items").val(x);
                    
    //             });
    //             $("#date").val(response.data.requestedDate);
    //             $("#requested-person").val(response.data.requestedPerson);
    //     })
    //     .catch(function (error) {
    //         // handle error
    //         console.log(error);
    //     });
    // });
    
// get the information of the site manager when site id is given
    // $('#site-id').on('input',function(e){
    //     let data = {
    //       siteId : $('#site-id').val()
    //     }
    //      console.log(data.siteId);
    //     axios.get(BASE_URL_LOCAL + '/employee/site-manager/sites/'+ data.siteId)
    //     .then(function (response) {
    //             $("#nic").val(response.data.nic);
    //             $("#site-manager-id").val(response.data.employeeId);
    //             $("#site-manager-name").val(response.data.employeeName);
    //     })
    //     .catch(function (error) {
    //         // handle error
    //         console.log(error);
    //     });
    // });

    // get the information of the site when site id is given
    $('#site-id_').on('input',function(e){
        let data = {
          siteId : $('#site-id_').val()
        }
         console.log(data.siteId);
        axios.get(BASE_URL_LOCAL + '/site/'+ data.siteId)
        .then(function (response) {
                $("#site-name").val(response.data.siteName);

        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });

        axios.get(BASE_URL_LOCAL + '/employee/site-manager/sites/'+data.siteId).then(function (response) {
            if (response.data) {
                console.log(response);
                $('#nic').val(response.data.nic);
                $('#site-manager-id').val(response.data.employeeId);
                $('#site-manager-name').val(response.data.employeeName);
            }
        }).catch(function (error) {
            console.log(error);
        });

    });

    function populateRequestDetails(){
        let x='';
        if (CURRENT_URL.includes('#') ) {
            // let siteId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.lastIndexOf('?'));
            let reqId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
           
            // $("#site-id").val(siteId);
            $("#request-id").val(reqId);
            let siteId = $("#request-id").val();

        axios.get(BASE_URL_LOCAL + '/requestmaterial/'+ reqId)
        .then(function (response) {
            console.log(response.data.items)
            // console.log(JSON.parse(response));
                // $("#items").val(response.data.orderId);
                $(function () {
                    $.each(response.data.items, function (i, item) {
                        console.log(i);
                        console.log(item);
                         x += i+" : "+item+' ';
                         console.log(x);
                       
                    });
                $("#items").val(x);
                    
                });
                $("#date").val(response.data.requestedDate);
                $("#requested-person").val(response.data.requestedPerson);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
    
        }
    }

    //site manager approving the request from the constructor
    function approveRequest(){
        let data = {
            requestId : $('#request-id').val(),
            siteId : $('#site-id').val(),
            siteManagerID : $('#site-manager-id').val(),
            siteManagerName : $('#site-manager-name').val(),
            requestedPerson : $('#requested-person').val(),
            isImmediated : $("[name='type']:checked").val(),
            requestedDate : $('#date').val(),
            isSiteManagerApproved : "1",
            isProcumentApproved:false
        }
        
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + data.requestId, data, {
            headers: headers
        })
        .then(function (response) {
            $.notify("Successfully Approved", "success");
        })
        .catch(function (error) {
            // handle error
            $.notify("Approval Declined", "error");      
        });
    }
    
     //site manager declining the request from the constructor
     function declineRequest(){
        let data = {
            requestId : $('#request-id').val(),
            siteId : $('#site-id').val(),
            siteManagerID : $('#site-manager-id').val(),
            siteManagerName : $('#site-manager-name').val(),
            requestedPerson : $('#requested-person').val(),
            isImmediated : $("[name='type']:checked").val(),
            requestedDate : $('#date').val(),
            isSiteManagerApproved : "2",
            isProcumentApproved:false
        }
        
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + data.requestId, data, {
            headers: headers
        })
        .then(function (response) {
            $.notify("Request Declined", "warn");
        })
        .catch(function (error) {
            // handle error
            $.notify("Request Declined", "error");      
        });
    }

    /***********  view approved/pending/rejected/immediate requests Starts ******************/

    function loadRequestsBasedOnStatus(){
        let status='';
        if (CURRENT_URL.includes('approved-requests')){
            status = 1;
        }
        else if (CURRENT_URL.includes('rejected-requests')){
            status = 2;
        }
        else if (CURRENT_URL.includes('pending-requests')){
            status = 0;
        }
        axios.get(BASE_URL_LOCAL + '/requestmaterial/request/'+ status)
        .then(function (response) {
            console.log(response)
            response.data.forEach(request => {
   
   
                var html = '<tr>';
                html +='<td align="right">'+request.requestId+'</td>' ;
                html +='<td align="right">' + request.requestedPerson +'</td>' ;
                html +='<td align="right">' + request.requestedDate + '</td>' ;
                html +='<td align="right">' + request.siteId + '</td>' ;
                html +='<td align="right">' + getItemList(request.items) + '</td>' ;
                html +='<td align="right">' + getImmediateLabels(request.isImmediated) + '</td>';             
                html +='</tr>';
               $('#requests-on-status tbody').append(html);
            });
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
   }

   function loadRequestsBasedOnImmediate(){
       let immediate = 1;
    axios.get(BASE_URL_LOCAL + '/requestmaterial/request/immediate/'+ immediate)
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {


            var html = '<tr>';
            html +='<td align="right">'+request.requestId+'</td>' ;
            html +='<td align="right">' + request.requestedPerson +'</td>' ;
            html +='<td align="right">' + request.requestedDate + '</td>' ;
            html +='<td align="right">' + request.siteId + '</td>' ;
            html +='<td align="right">' + getItemList(request.items) + '</td>' ;
            html +='<td align="right">' + getImmediateLabels(request.isImmediated) + '</td>';             
            html +='</tr>';
           $('#requests-on-immediate tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

   /***********  view approved/pending/rejected/immediate requests ends ******************/


/***********  Rate Supplier/Constructor Starts ******************/

function addRatings(){
    let data='';
    if (CURRENT_URL.includes('rate-supplier')){    
	 data = {
		purchaseOrderReference : $('#purchase-order-reference').val(),
		supplierId : $('#supplier-ID').val(),
        supplierName : $('#supplier-name').val(),
        deliveryEfficiency : $("[name='delivery']:checked").val(),
        supportiveness : $("[name='support']:checked").val(),
        overallRate : $("[name='overall']:checked").val(),
		feedback : $('#comments').val()
		
    }
}
    else if (CURRENT_URL.includes('rate-constructor')){    
        data = {
           constructorId : $('#constructor-ID').val(),
           constructorName : $('#sconstructor-name').val(),
           workOnTime : $("[name='work']:checked").val(),
           supportiveness : $("[name='support']:checked").val(),
           overallRate : $("[name='overall']:checked").val(),
           feedback : $('#comments').val()
           
       }
}
	axios.post(BASE_URL_LOCAL + '/ratings', data)
    .then(function (response) {
    	$.notify("Successfully Rated", "success");
    })
    .catch(function (error) {
        // handle error
        $.notify("Rating Failed", "error");
    });
}


function clearRatings(){
    if (CURRENT_URL.includes('rate-supplier')){    
        
           $('#purchase-order-reference').val(''),
           $('#supplier-ID').val(''),
           $('#supplier-name').val(''),
           $("[name='delivery']:checked").val(''),
           $("[name='support']:checked").val(''),
           $("[name='overall']:checked").val(''),
           $('#comments').val('')
           
       
   }
       else if (CURRENT_URL.includes('rate-constructor')){    

            $('#constructor-ID').val(''),
            $('#constructor-name').val(''),
            $("[name='work']:checked").val(''),
            $("[name='support']:checked").val(''),
            $("[name='overall']:checked").val(''),
            $('#comments').val('')
              
          
   }
}

/***********  Rate Supplier/Constructor Ends ******************/


/***********  View Purchased Orders Starts ******************/
function loadPurchasedOrders(){
    axios.get(BASE_URL_LOCAL + '/order/all')//todo
    .then(function (response) {
        console.log(response)
        response.data.forEach(request => {


            var html = '<tr>';
            html +='<td align="right">'+request.orderId+'</td>' ;
            html +='<td align="right">' + getOrderList(request.items) + '</td>' ;
            html +='<td align="right">'+formatDate(request.orderDate)+'</td>' ;
            html +='<td align="right">'+formatDate(request.returnedDate)+'</td>' ;
            html +='<td align="right">'+'<input type = "date" style="width:140px" id="returnedDate"/>'+'</td>' ;
            html +='<td align="right">' + getOrderStatusLabels(request.orderStatus) + '</td>';
             html +='<td align="right">' +
           '<a href="#" title="" class="btn btn-primary btn-sm" onclick="getOrderPurchasedFullyDelivered(this)">\n' +
           '        <span class="fa fa-hourglass" aria-hidden="true"></span>\n' +
           '        <span><strong>Fully Delivered</strong></span></a>'+
            '</a>' +
           '</td>' ;
             html +='<td align="right">' +
            '<a href="#" title="" class="btn btn-danger btn-sm" onclick="getOrderPurchasedPartiallyDelivered(this)">\n' +
           '        <span class="fa fa-hourglass-end" aria-hidden="true"></span>\n' +
           '        <span><strong>Partially Delivered</strong></span></a>'+
            '</a>' +
           '</td>' ;
            html +='</tr>';
           $('#view-purchased-orders tbody').append(html);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

function getOrderList(items){
	var html = '';
	for (var key = 0; key < items.length; key++) {
			html += items[key].itemName +' : '+items[key].quantity+'<br/>';	
	}
	
	return html;
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

window.getOrderPurchasedFullyDelivered = function(ele) {
    var row = $(ele).closest('tr');
    let orderPurchased = {
        orderId: row.find('td:first').text(),
        orderDate: row.find('td:nth-child(3)').text(),   
        returnedDate: $('#returnedDate').val(),  
        orderStatus: "Complete",   
        onHold:false
        
    }
        axios.put(BASE_URL_LOCAL + '/order/' + orderPurchased.orderId, orderPurchased, {
        headers: headers
    })
        .then(response => {
            console.log(response.orderPurchased);
                   $.notify("Order Marked as Fully Delivered", "success");
                    loadRequestsFromConstructor();
                    
                
                   
        })
        .catch(error => {
            console.log(error);
                    $.notify("Order Marking Error", "error");           
        })


}


window.getOrderPurchasedPartiallyDelivered = function(ele) {
    var row = $(ele).closest('tr');
    let orderPurchased = {
        orderId: row.find('td:first').text(),
        orderDate: row.find('td:nth-child(3)').text(),   
        returnedDate: $('#returnedDate').val(),  
        orderStatus: "Partial",
        onHold:false
        
    }
        axios.put(BASE_URL_LOCAL + '/order/' + orderPurchased.orderId, orderPurchased, {
        headers: headers
    })
        .then(response => {
            console.log(response.orderPurchased);
                   $.notify("Order Marked as Partially Delivered", "success");
                   loadPurchasedOrders();
        })
        .catch(error => {
            console.log(error);
            $.notify("Order Marking Error", "error");  
        })

        

}
/***********  View Purchased Orders Ends ******************/

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

/***********  Update Site with Items starts ******************/
function addItemToSite1() {
	if ($('#item-qty').val() != "") {

		let data = {
			itemId: $('#op-item-select').val(),
			itemName: $('#op-item-select :selected').text(),
			itemQty: $('#item-qty').val()
		}
		itemsArray.push(data);
		localStorage.setItem('items', JSON.stringify(itemsArray));

		loadItemTable1();
	} else {
		$.notify("Quantity cannot be empty", "error");
	}
}

function loadItemTable1() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	console.log(storedItems)

	for (var i of storedItems) {
		console.log(i.itemId + " | " + i.itemName);
		var html = '<tr>';
		html += '	<td class="nr-itemId" align="right">' + i.itemId + '</td>';
		html += '	<td class="text-right">' + i.itemName + '</td>';
		html += '	<td class="text-right">' + i.itemQty + '</td>';
		html += '	<td class="text-right">' +
			'		<a align="right" href="#" title="" class="btn btn-danger btn-sm">\n' +
			'        	<span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
			'        	<span><strong>Remove</strong></span></a>' +
			'		</a>' +
			'	</td>';
		html += '</tr>';
	}

	$('#site-item-table tbody').append(html);
}


function generateItemSelectDropdown1() {
	axios.get(BASE_URL_LOCAL + '/item/')
		.then(function (response) {
			console.log(response.data)
			var html = '<select class="form-control" id="op-item-select">';

			Object.keys(response.data).forEach(supplierAsKey => {
				response.data[supplierAsKey].forEach(item => {
					html += '<option value="' + item.itemId + '">' + item.itemName + '</option>';
				});
			});


			html += '</select>';
			$('#item-select').append(html);
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}


$(document).on('click', '#site-item-table .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var itemId = $(this).closest("tr").find(".nr-itemId").text();
	console.log(itemId);
	var r = confirm("Are you sure want to remove this item from this site?");
	if (r == true) {


		let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];

		var index = storedItems.findIndex(function (item, i) {
			return item.itemId === itemId
		});

		console.log("Index : " + index);
		if (index != -1) {
			storedItems.splice(index, 1);
		}

		localStorage.setItem('items', JSON.stringify(storedItems));
		$("#item-list tbody").empty();

		loadItemTable1();


		$.notify(itemId, "success");
	}
});

//  get the information of the site when site id is given
    $('#site-id').on('input',function(e){
        let data = {
          siteId : $('#site-id').val()
        }
         console.log(data.siteId);
        axios.get(BASE_URL_LOCAL + '/site/'+ data.siteId)
        .then(function (response) {
                $("#site-name").val(response.data.siteName);
                $("#site-address").val(response.data.address);

        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
    });


function updateSiteStock() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};
	for (var i of storedItems) {
		obj[i.itemName] = i.itemQty;
	}

	let data = {
        siteId : $('#site-id').val(),
		items: obj,
	}

	axios.put(BASE_URL_LOCAL + '/site/' + data.siteId, data)
		.then(function (response) {
            $.notify("Site Updated With Items", "success");
            $('#site-id').val('');
            $("#site-name").val('');
            $("#site-address").val('');
		})
		.catch(function (error) {
			// handle error
			console.log(error);
			$.notify("Site Updation Failed", "error");;
		});
}

/***********  Update Site with Items ends ******************/


/***********  Add Constructor Starts ******************/

function addConstructors(){
    let data=''; 
	 data = {
		employeeId : $('#employee-id').val(),
		employeeType : $("#employee-type").val(),
        employeeName : $('#name').val(),
        address : $("address").val(),
        email : $("email").val(),
		phone : $('#phone').val(),
		site : $('#site-ID').val()
		
    }

	axios.post(BASE_URL_LOCAL + '/employee/constructor', data)
    .then(function (response) {
    	$.notify("Successfully Added", "success");
    })
    .catch(function (error) {
        // handle error
        $.notify("Employee cannot add", "error");
    });
}



/***********  Add Constructor Ends ******************/