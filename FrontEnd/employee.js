const url = "http://localhost:3000";

document.getElementById("getReimbButton").onclick = getReimb
document.getElementById("insertReimb").onclick = insertReimb

async function insertReimb(){
    //Getting user inputted values
    let reimbAmount = document.getElementById("reimbAmount").value;
    let reimbType = document.getElementById("reimbType").value;


    let response = await fetch(url + "/insert", {method:"POST",
        body: JSON.stringify({"reimb_amount":reimbAmount,"reimb_resolved":null,"reimb_author_fk":sessionStorage.getItem("user_id"),"reimb_status_id_fk":1,"reimb_type_id_fk":reimbType})})
    
    
}

async function getReimb(){
    console.log(sessionStorage.getItem("user_id"))
    let response = await fetch(url + "/reimbursements/" + sessionStorage.getItem("user_id"))
    
    if(response.status === 200){
        let data = await response.json();

        let table = document.getElementById("reimbBody");
        for(var i = 1;i<table.rows.length;){
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
            cell6.innerHTML = reimbursement.reimb_status.reimb_status;
            row.appendChild(cell6);

            document.getElementById("reimbBody").appendChild(row);
        }
    }
}