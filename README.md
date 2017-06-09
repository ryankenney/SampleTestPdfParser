SampleTestPdfParser
================

Overview
----------------

This tool allows you to convert PDF files from [www.actualtests.com](www.actualtests.com)
into flash card sets for [Anki](https://apps.ankiweb.net/).

[www.actualtests.com](www.actualtests.com): A great site for sample tests for certifications.

* One-time fee for all sample tests for life.

[Anki](https://apps.ankiweb.net/): A handy little flashcard tool.

* Free on PC and Android. Fee on iPhone.


Using the Tool
----------------

Checkout and compile:

	git clone https://github.com/ryankenney/SampleTestPdfParser
	cd SampleTestPdfParser
	mvn install

Download your PDF file from [www.actualtests.com](www.actualtests.com).

* Let's use "SY0-401.pdf" as an example.

Convert your PDF file from a PDF to a text document (using a standard linux tool):

	pdftotext SY0-401.pdf SY0-401.txt

Convert your text file into an anki file:

	cd SampleTestPdfParser
	java -jar target/ActualTestPdfParser.jar -i SY0-401.txt -o SY0-401.pdf.anki

Loading into Anki
----------------

You have to use the deskop app to load the file onto your phone.

### Loading into the Anki Desktop App

Install [Anki](http://ankisrs.net/). It's available in the Ubuntu repos, but you'll need to manually download the latest .deb if you want to sync with the web and get the "deck" on your phone.

Inside of the Anki desktop app (in my case, Ubuntu), define a new deck:

* Click the "Create Deck" button
* Name the deck (e.g. "Security Plus") 

Inside of the Anki desktop app, import the questions file:

* Click the "Import" button
* Select the file
* Specify your "Deck"
* Enable "Allow HTML in fields"
* Click the "Import" button 

NOTE: If you've imported file into another deck, it won't import the entries again (duuumb)

NOTE: If you want to blow away all of your local Anki state, delete the "~/Anki" directory (at least this is true on linux)

Once you have the questions imported, it'll probably have a number in blue (probably "40"). This is the number of new flash cards you have scheduled to review today. You can adjust the preferences of each deck to prompt you for a certain number of questions per day. 

### Loading into the Anki Phone App

Click the sync button in the upper right corner of the desktop app to initiate a sync. This provides you a way to create an account. Once you get your account going, sync your deck, and setup your phone app with the same account. 

