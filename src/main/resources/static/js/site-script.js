/**
 * created by viraj
 **/

/* * * * *     Global Variables     * * * * */
let BASE_URL_LOCAL = 'http://localhost:9000';
let USER_INFO = 'user-info'
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

// if (CURRENT_URL.includes('manage-sites')) {
//     console.log("You are on Manage Sites page")
// }

// if (CURRENT_URL.includes('view-ratings')) {
//     console.log("You are on View Ratings page")
// }



function loadRequestsFromConstructor(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
     .then(function (response) {
    	 console.log(response)
    	 response.data.forEach(request => {


             var html = '<tr id="somerow">';
             html +='<td>'+request.orderId+'</td>' ;
             html +='<td class="nr-fid" scope="row">' + request.requestedPerson + '</td>' ;
             html +='<td >' + request.requestedDate + '</td>' ;
             html +='<td >' + request.siteId + '</td>' ;
             html +='<td >' + getItemList(request.items) + '</td>' ;
             html +='<td class="text-center">' + getImmediateLabels(request.isImmediated) + '</td>';
             html +='<td class="text-center">' + getApprovedLabels(request.isSiteManagerApproved) + '</td>' ;
             html +='<td><center>' +
            '<a href="#" title="" class="btn btn-primary btn-sm" onclick="getConfirmation(this)">\n' +
            '        <span class="far fa-check-square" aria-hidden="true"></span>\n' +
            '        <span><strong>Approve</strong></span></a>'+
            '</a></center>' +
            '</td>' ;
             html +='<td><center>' +
             '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
            '        <span class="far fa-minus-square" aria-hidden="true"></span>\n' +
            '        <span><strong>Decline</strong></span></a>'+
            '</a></center>' +
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
    var retVal = confirm("Are you sure you want to update ?");
    if( retVal == true ){
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
       
    
        axios.put(BASE_URL_LOCAL + '/requestmaterial/' + form3Data.orderId, form3Data, {
            headers: headers
        })
            .then(response => {
                console.log(response.form3Data);
                alert("Successfully updated Your Data!");           
            })
            .catch(error => {
                console.log(error);
                alert("One or More fields are empty!");            
            })
        // return true;
    }
    else{
        alert ("User does not want to update!");
        // return false;
    }

   

}

// setTimeout(function(){
//     $( "#view-requests" ).load( "view-requests-sitemanager.html #view-requests" );
//  }, 1000); //refresh every 1 second


function getItemList(items){
	var html = '<ul>';
	for (var key in items) {
		if (items.hasOwnProperty(key)) {
			html += '<li>'+ key +' - '+items[key]+'</li>';
		}
	}
	html +=  '</ul>';
	return html;
}

function getApprovedLabels(status){
	var btnClass = (status == 1) ? "btn btn-success btn-sm" : "btn btn-secondary btn-sm" ;
	var btnText = (status == 1) ? "Approved" : "Pending" ;
	var isDisabled = (status == 1||status == 0) ? "disabled" : "" ;

if(btnText == "Pending"){
	var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-question" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
            '</button>';
}
else{
    var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-check" aria-hidden="true"></span>' +
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


