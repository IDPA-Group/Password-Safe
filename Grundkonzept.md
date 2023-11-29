# Grundkonzept

| Bezeichnung    | Angaben        |
| -------------- | ---------------|
| Autoren:         | Justin Calle, David Hofstetter   |
| Erstellt am:   | 8.11.2023      |
| Git-Repo-URL   | [Repository](https://github.com/justindavidcalle/multimediaprojekt) |

## Aufbau
Die Full-Stack-Applikation agiert als Password-Manager, nutzt MongoDB als Datenbank, eine Spring Boot-Anwendung im Backend und React im Frontend. Der Zugang erfolgt über ein Masterpasswort, nach dem Einloggen können Nutzer auf einer Seite ihre allgemeinen Passwörter mit zugehörigem Benutzernamen oder E-Mail speichern. Das Masterpasswort wird sicher in der Datenbank gespeichert mit BCrypt.

Die Anwendung bietet:

* Login mit Masterpasswort: Sicherer Zugriff durch die Eingabe des Masterpassworts.

* Passwortverwaltung: Nach dem Login können Benutzer auf einer dedizierten Seite ihre allgemeinen Passwörter speichern.

Technologien:

* Datenbank: MongoDB sichert die Passwortdaten.

* Backend: Spring Boot gewährleistet eine robuste und skalierbare Serverseite.

* Frontend: React bietet eine benutzerfreundliche Oberfläche.

Die Password-Manager-Applikation verbessert die Sicherheit und ermöglicht eine zentrale Verwaltung sensibler Zugangsdaten. Die klare Trennung von Frontend und Backend ermöglicht eine effiziente und sichere Verarbeitung.

![Aufbau](/images/Template-Grundaufbau.png)


## Vorlage 

![VorlageLoginpage](/images/Template-Loginpage.jpg)

![VorlageManagement](/images/Template-Management.jpg)

## Reflexion 15.11.2023

### Justin Calle

Reflexion von Tätigkeiten:

Am Backend und der Verknüpfung mit MongoDB weitergearbeitet. Neue Klassen erstellt und Verknüpfung nun Vollständig. Angefangen mit RESTController für Login und Backend fungiert mit der Datenbank. Probleme mit der Datenbank. Es verknüpft mit einer Datenbank "Test" anstelle von "passwordsafe".

### David Hofstetter


Reflexion von Tätigkeiten.

Ich habe am Frontend gearbeitet, die Login Seite ist fertig, nur noch der Button muss mit der Password Safe Seite verknüpft werden. Bei der Password Safe Seite kann man mittlerweile Blocks hinzufügen und erstellen. Jedoch noch nicht löschen oder bearbeiten.

# Reflexion 22.11.2023

### Justin Calle


Das Backend wurde umfassend entwickelt, um eine nahtlose Verknüpfung mit dem Frontend zu gewährleisten. Sämtliche CRUD-Funktionen für die Passwörter sowie die Funktionen für das Login und die Registrierung wurden erfolgreich implementiert. Das Masterpasswort wird sicher durch BCrypt gehandhabt, während die Passwortblöcke effektiv verschlüsselt sind.

Darüber hinaus wurde besonderes Augenmerk darauf gelegt, die Systeme für die sichere Verwaltung von Passwörtern zu optimieren. Die Implementierung umfasst fortschrittliche Verschlüsselungstechniken, um die Vertraulichkeit der Nutzerdaten zu gewährleisten.


### David Hofstetter


Das Frontend-Projekt wurde erfolgreich abgeschlossen, alle erforderlichen Komponenten sind implementiert und das Styling wurde mittels CSS professionell umgesetzt. Der reibungslose Betrieb des Logout-Buttons, der mithilfe der useNavigate()-Funktion erstellt wurde, rundet die Frontend-Entwicklung erfolgreich ab.

Trotz einiger anfänglicher Herausforderungen beim Styling konnten sämtliche Schwierigkeiten durch gezielte Recherche, insbesondere durch Google, erfolgreich überwunden werden.

