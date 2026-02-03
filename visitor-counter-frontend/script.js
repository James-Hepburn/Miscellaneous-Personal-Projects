async function update_visitor_count () {
  var url = 'https://9nqbh63pt4.execute-api.us-east-1.amazonaws.com/Prod/visitors';
  var counter = document.getElementById ("visitor-count");

  try {
    var response = await fetch (url);
    var data = await response.json ();
    counter.textContent = data.count;
  } catch (error) {
    counter.textContent = "N/A";
  }
}

update_visitor_count ();
