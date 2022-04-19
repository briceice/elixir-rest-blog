import createView from "../createView.js";

export default function UserIndex(props) {
    console.log("The frontend did it. HER FAULT");
    return `
        <header>
            <h1>User Profile</h1>
        </header>
        <main>
            <div class="container-fluid">
                <div id="user-data-container">
                    ${props.users.map(user => `
                    <p class="username" data-id="${user.id}">Username: ${user.username}</p>
                    <p class="email" data-id="${user.id}">Email: ${user.email}</p>
                    <p class="password" data-id="${user.id}">Password: ${user.password}</p>
                    `).join('')}
                </div>
                <div id="edit-user-container">
                    <form>
                        <div class="form-group">
                            <label for="edit-user-password">Change Password</label>
                            <input type="text" class="form-control" id="edit-user-password" placeholder="Enter New Password">
                        </div>
                        <button type="submit" class="btn btn-primary" id="edit-password-btn">Submit</button>
                    </form>
                </div>
            </div>
        </main>
    `;
}

function editPassword(){
    $('#edit-password-btn').click(function(){

        let id = 1;
        let newPassword = $('#edit-user-password').val();

        let request = {
            method: "PUT"
        }
        fetch(`http://localhost:8080/api/users/${id}/updatePassword?newPassword=${newPassword}`, request)
            .then(res => {
                console.log(res.status);
                createView("/users")
            }).catch(error => {
                console.log(error);
                createView("/users");
        });
    })
}

export function UsersEvent(){
    editPassword();
}

