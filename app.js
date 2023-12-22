const express = require('express');
const bodyParser = require('body-parser');
const translationRouter = require('./translate.js');
const recordRouter = require('./server.js');

const app = express();
const PORT = process.env.PORT || 8080;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Use the translation router
app.use('/api/translate', translationRouter);

// Use the record router
app.use('/api/record', recordRouter);

app.get("/", (req, res) => {
    console.log("Response success");
    res.send("Success!");
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
