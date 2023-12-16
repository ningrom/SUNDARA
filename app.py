#instal dulu pip install -r requirements.txt

import win32com.client

def get_next_word(current_word):
    # Buka dokumen Word yang ada
    word = win32com.client.Dispatch('Word.Application')
    word.Visible = 0

    doc = word.Documents.Open('Dokumen.docx') # Ganti 'Dokumen.docx' dengan nama file dokumen Word yang ingin Anda buka

    # Cari kata yang sedang dicari di dokumen
    cursor = doc.Range().Find()
    cursor.Text = current_word

    # Cari kata berikutnya di dokumen
    cursor.Execute()
    cursor.Move(2) # 2 = wdCharacter

    # Ambil kata berikutnya
    next_word = cursor.Text

    # Tutup dokumen Word dan Word Itself
    doc.Close()
    word.Quit()

    return next_word

if __name__ == "__main__":
    current_word = input("Masukkan kata yang ingin dicari: ")
    next_word = get_next_word(current_word)
    print(f"Kata berikutnya adalah: {next_word}")
