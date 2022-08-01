const url = "http://localhost:3000";

document.getElementById("getReimbButton").onclick = getReimb
document.getElementById("reimbStatus").onchange = getByStatus

async function getReimb(){
    let response = await fetch(url + "/reimbursements/")
    
    if(response.status === 200){
        let data = await response.json();

        let table = document.getElementById("reimbBody");
        for(var i = 0;i<table.rows.length;){
            table.deleteRow(i);
        }

        for(let reimbursement of data){
            let row = document.createElement("tr");

            //cells for reimbursement's id number
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.reimb_id;
            row.appendChild(cell);

            //cells for reimbursement amount
            let cell2 = document.createElement("td");
            cell2.innerHTML = "$" + reimbursement.reimb_amount;
            row.appendChild(cell2);

            //cells for author
            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.reimb_author_fk;
            row.appendChild(cell3);

            //cells for resolver
            if(reimbursement.reimb_resolver_fk == false){
                let cell4 = document.createElement("td");
                cell4.innerHTML = "-";
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                cell4.innerHTML = reimbursement.reimb_resolver_fk;
                row.appendChild(cell4);
            }

            //cells for type
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.reimb_type.reimb_type;
            row.appendChild(cell5);

            //cells for status
            let cell6 = document.createElement("td");
            let statusArray = ["Pending", "Accepted", "Denied"];
            let selectList = document.createElement("select");
            cell6.appendChild(selectList);

            for (let i = 0; i < statusArray.length; i++){
                let option = document.createElement("option");
                option.value = i+1;
                option.text = statusArray[i];
                selectList.appendChild(option);
            }
            selectList.value = reimbursement.reimb_status.reimb_status_id;
            selectList.onchange = updateStatus;
            row.appendChild(cell6);

            document.getElementById("reimbBody").appendChild(row);
        }
    }
}

async function getByStatus(){
    let status = document.getElementById("reimbStatus").value;
    if(status == 0){

    }
    else{
        let response = await fetch(url + "/status/" + status)
        
        if(response.status === 200){
            let data = await response.json();

            let table = document.getElementById("reimbBody");
            for(var i = 0;i<table.rows.length;){
                table.deleteRow(i);
            }

            for(let reimbursement of data){
                let row = document.createElement("tr");

                //cells for reimbursement's id number
                let cell = document.createElement("td");
                cell.innerHTML = reimbursement.reimb_id;
                row.appendChild(cell);

                //cells for reimbursement amount
                let cell2 = document.createElement("td");
                cell2.innerHTML = "$" + reimbursement.reimb_amount;
                row.appendChild(cell2);

                //cells for author
                let cell3 = document.createElement("td");
                cell3.innerHTML = reimbursement.reimb_author_fk;
                row.appendChild(cell3);

                //cells for resolver
                if(reimbursement.reimb_resolver_fk == false){
                    let cell4 = document.createElement("td");
                    cell4.innerHTML = "-";
                    row.appendChild(cell4);
                } else {
                    let cell4 = document.createElement("td");
                    cell4.innerHTML = reimbursement.reimb_resolver_fk;
                    row.appendChild(cell4);
                }

                //cells for type
                let cell5 = document.createElement("td");
                cell5.innerHTML = reimbursement.reimb_type.reimb_type;
                row.appendChild(cell5);

                //cells for status
                let cell6 = document.createElement("td");
                let statusArray = ["Pending", "Accepted", "Denied"];
                let selectList = document.createElement("select");
                cell6.appendChild(selectList);

                for (let i = 0; i < statusArray.length; i++){
                    let option = document.createElement("option");
                    option.value = i+1;
                    option.text = statusArray[i];
                    selectList.appendChild(option);
                }
                selectList.value = reimbursement.reimb_status.reimb_status_id;
                selectList.onchange = updateStatus;
                row.appendChild(cell6);

                document.getElementById("reimbBody").appendChild(row);
            }
        }
    }
}

async function updateStatus(){
    let row = this.parentElement.parentElement;
    
    let reimbId = row.cells.item(0).innerHTML;
    let newStatus = this.value;
    console.log(sessionStorage.getItem("user_id"))
    let response = await fetch(url + "/status/" + reimbId, {
        method:"PUT",
        body: JSON.stringify({"reimb_resolver_fk":sessionStorage.getItem("user_id"), "reimb_status_id_fk":newStatus})
    })


}