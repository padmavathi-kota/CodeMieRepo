// script.js
document.getElementById("expense-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the form from submitting

//      // Check if the popup is already shown
//   const popup = document.getElementById("popup");
//   if (popup.classList.contains("show")) {
//     return;
//   }
  
    // Retrieve the form inputs
    const amount = document.getElementById("amount").value;
    const date = document.getElementById("date").value;
    const category = document.getElementById("category").value;
    const currency = document.getElementById("currency").value;
    const description = document.getElementById("description").value;
  
    // Log the expense details in the console
    console.log("Amount:", amount);
    console.log("Date:", date);
    console.log("Category:", category);
    console.log("Currency:", currency);
    console.log("Description:", description);
    // Show the popup
  popup = document.getElementById("popup");
  popup.classList.add("show");
  setTimeout(function() {
    popup.classList.remove("show");
  }, 3000); // Hide the popup after 3 seconds
  // Create a JavaScript object to store the data
  // Create an object with the form data
  const formData = {
    amount: amount,
    date: date,
    currency: currency,
    category: category,
    description: description
};

// Send the form data to the Java backend
//fetch('/save', {
//    method: 'POST',
//    headers: {
//        'Content-Type': 'application/json'
//    },
//    body: JSON.stringify(formData)
//})
//.then(response => response.json())
//.then(data => {
//    // Handle the response from the backend
//    console.log(data);
//})
//.catch(error => {
//    // Handle any errors that occur during the request
//    console.error(error);
//});
//    // Clear the form inputs
//    document.getElementById("amount").value = "";
//    document.getElementById("date").value = "";
//    document.getElementById("category").value = "";
//    document.getElementById("description").value = "";
//	 document.getElementById("currency").value = "";
//    
  });  