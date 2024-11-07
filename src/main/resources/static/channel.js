document.addEventListener('DOMContentLoaded', function () {
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const messageForm = document.getElementById('messageForm');
    const messagesContainer = document.getElementById('messages-container');
    const messageList = document.getElementById('message-list');
    const channelId = messageForm.getAttribute('data-channel-id'); // Getting channel ID from form

    // Polling function to fetch new messages every 500ms
    function pollMessages() {
        if (channelId) {
            fetch(`/channels/${channelId}/messages`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(messages => {
                    messageList.innerHTML = ''; // Clear current messages
                    messages.forEach(message => {
                        const li = document.createElement('li');
                        li.innerHTML = `<strong>${message.username}</strong>: ${message.content}`;
                        messageList.appendChild(li);
                    });

                    // Scroll to the bottom to see the latest message
                    messagesContainer.scrollTop = messagesContainer.scrollHeight;
                })
                .catch(error => console.error('Error polling messages:', error));
        }
    }

    // Set up polling every 500ms
    setInterval(pollMessages, 500);

    // Listen for "Enter" key press to trigger the Send button
    messageInput.addEventListener('keydown', function (event) {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault(); // Prevent new line
            sendButton.click(); // Simulate a click on the Send button
        }
    });

    // Handle sending the message
    messageForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the form from submitting in the traditional way

        const messageContent = messageInput.value.trim();
        if (messageContent !== '') {
            fetch(`/channels/${channelId}/message`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    content: messageContent
                })
            }).then(response => {
                if (response.ok) {
                    messageInput.value = ''; // Clear the input field after sending
                    pollMessages(); // Fetch updated messages after sending
                } else {
                    console.error('Error sending message:', response.statusText);
                }
            }).catch(error => console.error('Error sending message:', error));
        }
    });
});
