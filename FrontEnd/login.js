const url = "http://localhost:3000";

document.getElementById("loginButton").onclick = login

async function login() {
    let user = document.getElementById("username").value
    let pass = document.getElementById("password").value

    let userCred = {
        username: user,
        password: pass
    }

    let response = await fetch(url + "/login", {
        method: "POST",
        body: JSON.stringify(userCred),
        credentials: "include"
    })

    

    if (response.status === 202) {
        let data = await response.json();
        console.log(data);
        sessionStorage.setItem("user_id", data.ers_users_id)
        //determining where to redirect depending on result
        if(data.user_role.ers_user_role_id == 1){
            location.href = "employee.html";
        } else {
            location.href = "manager.html";
        }

    } else {
        
    }
}