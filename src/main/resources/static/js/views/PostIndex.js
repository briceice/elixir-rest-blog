import createView from "../createView.js";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div class="container-fluid">
                <div id="posts-container">
                    ${props.posts.map(post => `<h3>${post.title}</h3>
                    <p>${post.content}</p>
                    <p>Author: ${post.user.username}</p>
                    <button type="submit" class="btn btn-primary edit-post-btn" data-id="${post.id}">Edit Post</button>
                    <button type="submit" class="btn btn-primary delete-post-btn" data-id="${post.id}">Delete Post</button>
                    `).join('')}
                </div>
    
                <div id="add-post-container">
                    <form>
                        <div class="form-group">
                            <label for="add-post-title">Title</label>
                            <input type="text" class="form-control" id="add-post-title" placeholder="Enter Title">
                        </div>
                        <div class="form-group">
                            <label for="add-post-content">Content</label>
                            <textarea class="form-control" id="add-post-content" rows="3" placeholder="Enter Content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary" id="add-post-btn">Submit New Post</button>
                    </form>
                </div>
            </div>
        </main>
    `;
}

function submitPost() {
    $('#add-post-btn').click(function(){

        let title = $('#add-post-title').val();
        let content = $('#add-post-content').val();
        let post = {
            title,
            content
        }

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(post)
        }

        fetch("http://localhost:8080/api/posts", request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
                console.log(error);
                createView("/posts");
        });
    })
}

function editPost() {
    $('.edit-post-btn').click(function(){

        let title = $('#add-post-title').val();
        let content = $('#add-post-content').val();
        let post = {
            title,
            content
        }

        let postId = this.getAttribute('data-id')

        let request = {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(post)
        }

        fetch(`http://localhost:8080/api/posts/${postId}`, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });
    })
}

function deletePost() {
    $('.delete-post-btn').click(function(){

        let postId = this.getAttribute('data-id')

        let request = {
            method: "DELETE"
        }

        fetch(`http://localhost:8080/api/posts/${postId}`, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });

    })
}

export function PostsEvent(){
    submitPost();
    editPost();
    deletePost();
}

