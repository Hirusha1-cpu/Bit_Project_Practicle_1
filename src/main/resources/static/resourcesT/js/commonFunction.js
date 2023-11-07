//define function for ajax request(POST, PUT, DELETE)
const ajaxRequestBody = (url, method, object)=>{
    let serverResponse;
    $.ajax(url, {
        async: false,
        type: method,
        data: JSON.stringify(object),
        contentType: 'application/json',
        success: function (data, status, ahr) {
            console.log(url+ "\n "+"success" + status + " " + ahr);
            serverResponse = data;
        },
        error: function (ahr, status, errormsg) {
            console.log(url+ "\n "+"Fail" + errormsg + " " + status);
            serverResponse = errormsg;
        }
    })
    return serverResponse;
}

// define function for fill data into select element
const fillDataIntoSelect = (fieldId,message, dataList, property, selectedValue)=>{
    fieldId.innerHTML = '';
    const optionMsg = document.createElement('option');
    optionMsg.value = ""
    optionMsg.innerText = message;
    optionMsg.selected = 'selected';
    optionMsg.disabled = 'disabled';
    fieldId.appendChild(optionMsg)
    
    dataList.forEach(element => {
        const option = document.createElement('option');
        option.value = JSON.stringify(element);
        option.innerText = element[property];
        if(selectedValue == element[property]){
            option.selected = 'selected';
        }
        fieldId.appendChild(option);
    })

}