const http = require('http');
const path = require('path');

const server = http.createServer((req, res) => {
  if (req.url === path.normalize('/movie/random')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.write(`{
      "title": "Raiders of the Lost Ark",
      "pt_title": "Indiana Jones e Os Caçadores da Arca Perdida",
      "year": "1981"
    }`);
    res.end()
  } else {
    res.writeHead(404, { 'Content-Type': 'text/plain' });
    res.end("The requested resource was not found!");
  }
}).listen(3000, function() {
  console.log("Server started at port 3000.")
});