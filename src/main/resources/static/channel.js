document.addEventListener("DOMContentLoaded", function () {
    const messageForm = document.getElementById("messageForm");
    const messageInput = document.getElementById("messageInput");
    const messageContainer = document.getElementById("message-container");

    const channelId = window.location.pathname.split("/")[2]; // Get channelId from URL

    // Function to send a message
    messageForm.addEventListener("submit", function (e) {
        e.preventDefault(); // Prevent form submission from reloading the page

        const messageContent = messageInput.value;

        // Send the message to the server
        fetch(`/channels/${channelId}/message`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams({
                content: messageContent
            })
        }).then(() => {
            messageInput.value = ""; // Clear the input field after sending
            fetchMessages(); // Fetch new messages after sending
        });
    });

    // Function to fetch and display messages
    function fetchMessages() {
        fetch(`/channels/${channelId}/messages`)
            .then(response => response.json())
            .then(messages => {
                messageContainer.innerHTML = ""; // Clear existing messages
                messages.forEach(message => {
                    const messageElement = document.createElement("div");
                    messageElement.textContent = `${message.username}: ${message.content}`;
                    messageContainer.appendChild(messageElement);
                });
            });
    }

    // Polling for new messages every 500 milliseconds
    setInterval(fetchMessages, 500);
});
