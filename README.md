Εφαρμογή Διαχείρισης Αποζημιώσεων
Η Εφαρμογή Διαχείρισης Αποζημιώσεων είναι μια πλατφόρμα για την υποβολή και διαχείριση αιτήσεων αποζημίωσης.

Οδηγίες Εγκατάστασης
Εγκατάσταση npm:

Κατεβάστε και εγκαταστήστε το Node.js από την επίσημη ιστοσελίδα.
Η εγκατάσταση θα περιλαμβάνει επίσης το npm (Node Package Manager).
Εγκατάσταση Εξαρτήσεων Εφαρμογής:

Στον φάκελο του project, εκτελέστε την εντολή:
npm install
Εκτέλεση Εφαρμογής:
For developers
(Στον φάκελο του frontend project, εκτελέστε την εντολή:
npm run build
Αφού τρέξετε αυτήν την εντολή θα δημιουργηθεί ένας φάκελος dist. Παίρνετε τα αρχεία και τα μεραφέρετε στον φάκελο demo\src\main\resources\static.)
Με αυτά τα βήματα, μπορείτε να εγκαταστήσετε και να τρέξετε την εφαρμογή, εκτελώντας την εντολή mvn spring-boot:run στο backend. Θα πρέπει να χρησιμοποιήσετε μία εφαρμογή όπως το 
POSTMAN για να δημιουργήσετε έναν χρήστη με POST στο http://localhost:8080/api/farmers και στην συνέχεια με τα στοιχεία που θα έχει ο χρήστης να κάνετε είσοδο στην εφαρμογή στον http://localhost:8080/ .
Στο body θα μπορούστε να τρέξετε σαν παράδειγμα αυτά τα στοιχεία σε JSON μορφή 
{
    "name" : "Marios Milas",
    "email": "mmilas@gmail.com",
    "password" : "password"
}
και στην συνέχεια με τα ίδια στοιχεία να συνδεθείτε και στην εφαρμογή όπου μετά μπορείτε να φτιάξετε νέο χρήστη, να συμπληρώσετε ένα petition, να δημιουργήσετε έναν inspector και να κάνετε approve ή reject το petition ως inspector.
Σημείωση: από λάθος δικό μας ο inspector πρέπει να έχει τα στοιχεία username: admin@example.com password: adminpassword 
Καλή τύχη!

