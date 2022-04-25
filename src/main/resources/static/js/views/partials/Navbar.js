import {isLoggedIn} from "../../auth.js";

export default function Navbar(props) {
    const loggedIn = isLoggedIn();
    let html =  `
        <nav>
            <a href="/" data-link>Home</a>
        `

    if (loggedIn){
        html += `
            <a href="/posts" data-link>Posts</a>
            `
    }

    html += `
            <a href="/about" data-link>About</a>
            `

    if (loggedIn){
        html += `
            <a href="/users" data-link>User</a>
            <a href="/logout" data-link>Logout</a>
            `
    }

    if (!loggedIn){
        html += `
            <a href="/register" data-link>Register</a>
            <a href="/login" data-link>Login</a>
            `
    }

    html += `
        </nav>
    `;

    return html;
}