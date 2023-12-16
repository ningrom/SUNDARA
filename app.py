pip install python-docx pywin32 #instal dulu

from flask import Flask, request, jsonify
import win32com.client

app = Flask(__name__)

@app.route('/')
def home():
    return 'Hello, world!'

@app.route('/next-sentence', methods=['POST'])
def next_sentence():
    word_doc = request.form['word_doc']
    current_sentence = request.form['current_sentence']

    try:
        word = win32com.client.Dispatch("Word.Application")
        word.Visible = 0
        word.Documents.Open(word_doc)

        selection = word.Selection
        selection.HomeKey(Unit=1)

        search_query = current_sentence.strip() + '.'
        found = selection.Find.Execute(search_query)

        if found:
            selection.MoveDown(Unit=1, Count=1)
            next_sentence = selection.Text.strip()

            if not next_sentence:
                next_sentence = "No more sentences."

            return jsonify({"success": True, "next_sentence": next_sentence})
        else:
            return jsonify({"success": False, "error": "Current sentence not found."})
    except Exception as e:
        return jsonify({"success": False, "error": str(e)})

if __name__ == '__main__':
    app.run(debug=True)
