document.addEventListener('DOMContentLoaded', function() {
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton'); // Grab the send button

    // Polling function to fetch new messages every 500ms
    function pollMessages() {
        const channelId = sendButton.getAttribute('data-channel-id'); // Get channel ID from the send button's data attribute
        console.log('Polling messages for channel ID:', channelId); // Debugging to verify correct channel ID
        fetch(`/channels/${channelId}/messages`)
            .then(response => response.json())
            .then(messages => {
                const messagesDiv = document.getElementById('messages');
                const messagesList = messagesDiv.querySelector('ul');
                messagesList.innerHTML = ''; // Clear current messages

                // Add each new message to the list
                messages.forEach(message => {
                    const li = document.createElement('li');
                    li.innerHTML = `<strong>${message.username}</strong>: ${message.content}`;
                    messagesList.appendChild(li);
                });
            })
            .catch(error => console.error('Error polling messages:', error));
    }

    // Set up polling every 500ms
    setInterval(pollMessages, 500);

    // Listen for "Enter" key press to trigger the Send button
    messageInput.addEventListener('keydown', function(event) {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault(); // Prevent new line on Enter
            console.log('Enter key pressed. Simulating button click.'); // Debugging
            sendButton.click();     // Simulate a click on the Send button
        }
    });
});
