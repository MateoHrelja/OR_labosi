const express = require('express');
const router = express.Router();
const db = require('../database');

router.get('/', async function (req, res) {
    const kukci2 = (await db.query('SELECT * FROM kukci2'));

    res.render('datatable', {
        title: 'Datatable',
        kukci2: kukci2,
    });
});

module.exports = router;