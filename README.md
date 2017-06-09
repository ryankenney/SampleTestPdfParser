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

