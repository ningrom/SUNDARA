const express = require('express');
const mysql = require('mysql');
const router = express.Router();
const Multer = require('multer');
const imgUpload = require('./uploadImg.js');

const multer = Multer({
  storage: Multer.MemoryStorage,
  fileSize: 5 * 1024 * 1024
});

// Use createPool instead of createConnection
const pool = mysql.createPool({
   host: '34.101.209.36',
   user: 'root',
   password: 'sundara',
   database: 'sundara-database'
});

// No need for connection.connect()

router.get('/list', (req, res) => {
    const query = "SELECT * FROM peribahasa";
    // Use a connection from the pool
    pool.query(query, (err, rows, field) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.json(rows);
        }
    });
});

router.get("/getPeribahasaById", (req, res) => {
    const st_id = req.query.st_id;
    const query = "SELECT * FROM peribahasa WHERE st_id = ?";
    
    // Use a connection from the pool
    pool.query(query, [st_id], (err, rows, field) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.json(rows);
        }
    });
});

router.get("/getPeribahasaBySentence", (req, res) => {
    const sentence = req.query.sentence;
    const query = "SELECT * FROM peribahasa WHERE sentence = ?";
    
    // Use a connection from the pool
    pool.query(query, [sentence], (err, rows, field) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.json(rows);
        }
    });
});

router.post('/insertPeribahasa', (req, res) => {
    const sentence = req.body.sentence;
    const description = req.body.description;

    const query = "INSERT INTO peribahasa (sentence, description) VALUES (?, ?)";
    
    // Use a connection from the pool
    pool.query(query, [sentence, description], (err, rows, field) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.send({ message: "Insert Successful" });
        }
    });
});

router.put('/updatePeribahasa', (req, res) => {
    const st_id = req.body.st_id;
    const sentence = req.body.sentence;
    const description = req.body.description;

    const query = "UPDATE peribahasa SET sentence = ?, description = ? WHERE st_id = ?";
    
    // Use a connection from the pool
    pool.query(query, [sentence, description, st_id], (err, rows, field) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.send({ message: "Update successful" });
        }
    });
});

router.delete("/deletePeribahasa", (req, res) => {
    const st_id = req.query.st_id;

    const query = "DELETE FROM peribahasa WHERE st_id = ?";
    
    // Use a connection from the pool
    pool.query(query, [st_id], (err, rows, fields) => {
        if (err) {
            console.error(`Error: ${err.sqlMessage}`);
            res.status(500).json({ error: 'Internal Server Error' });
        } else {
            res.send({ message: "Delete successful" });
        }
    });
});

module.exports = router;
