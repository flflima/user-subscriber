const http = require('http');
const path = require('path');

const movies = [
  {
    "id": "00001",
    "title": "Raiders of the Lost Ark",
    "pt_title": "Indiana Jones e Os Caçadores da Arca Perdida",
    "year": "1981"
  },
  {
    "id": "00002",
    "title": "Shrek",
    "pt_title": "Shrek",
    "year": "2001"
  }
]

const server = http.createServer((req, res) => {
  if (req.url === path.normalize('/movie/random')) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.write(JSON.stringify(movies[0]));
    res.end()
  } else {
    res.writeHead(404, { 'Content-Type': 'text/plain' });
    res.end("The requested resource was not found!");
  }
}).listen(3000, function () {
  console.log("Server started at port 3000.")
});