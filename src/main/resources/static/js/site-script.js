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

function loadRequestsFromConstructor(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
     .then(function (response) {
    	 console.log(response)
    	 response.data.forEach(request => {


             var html = '<tr>';
             html +='<td align="right">'+request.orderId+'</td>' ;
             html +='<td align="right">' + request.requestedPerson +'</td>' ;
             html +='<td align="right">' + request.requestedDate + '</td>' ;
             html +='<td align="right">' + request.siteId + '</td>' ;
             html +='<td align="center">' + getItemList(request.items) + '</td>' ;
             html +='<td align="right">' + getImmediateLabels(request.isImmediated) + '</td>';
             html +='<td align="right">' + getApprovedLabels(request.isSiteManagerApproved) + '</td>' ;
            //   html +='<td align="right">' +
            // '<a href="#" title="" class="btn btn-primary btn-sm" onclick="getConfirmation(this)">\n' +
            // '        <span class="far fa-check-square" aria-hidden="true"></span>\n' +
            // '        <span><strong>Approve</strong></span></a>'+
            //  '</a>' +
            // '</td>' ;
            //   html +='<td align="right">' +
            //  '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
            // '        <span class="far fa-minus-square" aria-hidden="true"></span>\n' +
            // '        <span><strong>Decline</strong></span></a>'+
            //  '</a>' +
            // '</td>' ;
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
            orderId: row.find('td:first').text(),
            requestedPerson: row.find('td:nth-child(2)').text(),   
            siteId: row.find('td:nth-child(4)').text(),  
            requestedDate: row.find('td:nth-child(3)').text(),     
            // items: row.find('td:nth-child(5)').text(), 
            isImmediated: row.find('td:nth-child(6)').text(),  
            isSiteManagerApproved: 1
            
        }
            axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + form3Data.orderId, form3Data, {
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
    if(status==1){
        btnClass = "btn btn-success btn-sm";
        btnText = "Approved";
        isDisabled = "disabled";
    }
    else if(status==2){
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

    $('#request-id').on('input',function(e){
        let x='';
        let data = {
          reqId : $('#request-id').val()
        }
         console.log(data.reqId);
        axios.get(BASE_URL_LOCAL + '/requestmaterial/'+ data.reqId)
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
    });
    
// get the information of the site manager when site id is given
    $('#site-id').on('input',function(e){
        let data = {
          siteId : $('#site-id').val()
        }
         console.log(data.siteId);
        axios.get(BASE_URL_LOCAL + '/employee/site-manager/sites/'+ data.siteId)
        .then(function (response) {
                $("#nic").val(response.data.nic);
                $("#site-manager-id").val(response.data.employeeId);
                $("#site-manager-name").val(response.data.employeeName);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        });
    });

    // get the information of the site when site id is given
    $('#site-id').on('input',function(e){
        let data = {
          siteId : $('#site-id').val()
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
    });

    //site manager approving the request from the constructor
    function approveRequest(){
        let data = {
            orderId : $('#request-id').val(),
            siteId : $('#site-id').val(),
            siteManagerID : $('#site-manager-id').val(),
            siteManagerName : $('#site-manager-name').val(),
            requestedPerson : $('#requested-person').val(),
            isImmediated : $("[name='type']:checked").val(),
            requestedDate : $('#date').val(),
            isSiteManagerApproved : "1"
        }
        
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + data.orderId, data, {
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
            orderId : $('#request-id').val(),
            siteId : $('#site-id').val(),
            siteManagerID : $('#site-manager-id').val(),
            siteManagerName : $('#site-manager-name').val(),
            requestedPerson : $('#requested-person').val(),
            isImmediated : $("[name='type']:checked").val(),
            requestedDate : $('#date').val(),
            isSiteManagerApproved : "2"
        }
        
        axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + data.orderId, data, {
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
                html +='<td align="right">'+request.orderId+'</td>' ;
                html +='<td align="right">' + request.requestedPerson +'</td>' ;
                html +='<td align="right">' + request.requestedDate + '</td>' ;
                html +='<td align="right">' + request.siteId + '</td>' ;
                html +='<td align="center">' + getItemList(request.items) + '</td>' ;
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
            html +='<td align="right">'+request.orderId+'</td>' ;
            html +='<td align="right">' + request.requestedPerson +'</td>' ;
            html +='<td align="right">' + request.requestedDate + '</td>' ;
            html +='<td align="right">' + request.siteId + '</td>' ;
            html +='<td align="center">' + getItemList(request.items) + '</td>' ;
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
	axios.post(BASE_URL_LOCAL + '/rating', data)
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