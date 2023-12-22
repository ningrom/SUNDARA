const express = require('express');
const { Translate } = require('@google-cloud/translate').v2;
const bodyParser = require('body-parser');
require('dotenv').config();

// Your credentials
const CREDENTIALS = JSON.parse(process.env.CREDENTIALS);

// Configuration for the client
const translate = new Translate({
    credentials: CREDENTIALS,
    projectId: CREDENTIALS.project_id,
});

const app = express();
const PORT = process.env.PORT || 8080;

app.use(bodyParser.json());

// Endpoint untuk terjemahan dari bahasa sumber ke bahasa target
app.post('/translate', async (req, res) => {
    const { text, targetLanguage } = req.body;

    try {
        // Detect language
        const sourceLanguage = await detectLanguage(text);

        // Translate text
        const translatedText = await translateText(text, targetLanguage);

        res.json({
            sourceLanguage,
            targetLanguage,
            translatedText,
        });
    } catch (error) {
        console.error(`Error: ${error.message}`);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

// Endpoint untuk terjemahan dari bahasa target ke bahasa sumber
app.post('/translate/backward', async (req, res) => {
    const { text, sourceLanguage } = req.body;

    try {
        // Translate text
        const translatedText = await translateText(text, sourceLanguage);

        res.json({
            sourceLanguage,
            targetLanguage: 'su',
            translatedText,
        });
    } catch (error) {
        console.error(`Error: ${error.message}`);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

const detectLanguage = async (text) => {
    try {
        const [detection] = await translate.detect(text);
        return detection.language;
    } catch (error) {
        console.error(`Error at detectLanguage: ${error.message}`);
        throw error;
    }
};

const translateText = async (text, targetLanguage) => {
    try {
        const [translation] = await translate.translate(text, targetLanguage);
        return translation;
    } catch (error) {
        console.error(`Error at translateText: ${error.message}`);
        throw error;
    }
};

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
