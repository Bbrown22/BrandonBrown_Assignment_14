document.addEventListener("DOMContentLoaded", function () {
    const channelId = document.getElementById('channel-id').value;
    const messageList = document.getElementById('message-list');

    // Function to fetch and update messages
    function fetchMessages() {
        fetch(`/channels/${channelId}/messages`)
            .then(response => response.json())
            .then(messages => {
                // Clear the current message list
                messageList.innerHTML = '';
                // Loop through messages and append them to the list
                messages.forEach(message => {
                    const messageElement = document.createElement('li');
                    messageElement.innerHTML = `<strong>${message.username}</strong>: ${message.content}`;
                    messageList.appendChild(messageElement);
                });
            })
            .catch(error => console.error('Error fetching messages:', error));
    }

    // Poll for new messages every 500ms
    setInterval(fetchMessages, 500);
});
