import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder
class LibraryParser
{
	public static void main(args)
	{

		println "*************************************************"
		println "*************Written by Ross Troha***************"
		println "*************September 11th 2013*****************"
		println "********linkedin.com/pub/ross-troha/42/39/252****"
		println "*************facebook.com/djtroha****************"
		println "*************soundcloud.com/djtroha**************"
		println "*************************************************"

		println "/////////////////////////////////////////////////"
		println "//////This script converts Traktor's open key////"
		println "//////to the more commonly used camelot scale.///"
		println "/////////////////////////////////////////////////"
		println "//////The converted keys will be stored in///////"
		println "//////the Key Text column in traktor/////////////"
		println "/////////////////////////////////////////////////"
		
		println "This script needs to be in the Traktor root directory or the same location as the collection file!"
		def xmlFile = new File("collection.nml")

		if(xmlFile.exists())
		{
			println "Collection file found!"
			def counter = 0
			println "parsing collection"
			def xml = new XmlParser().parse(xmlFile)
			xml.COLLECTION.ENTRY.each{ 
				
				if (it.MUSICAL_KEY[0] != null)
				{
					switch (it.MUSICAL_KEY[0].@VALUE)
					{
						case "0":
							it.INFO.@KEY ="08B"
							break
						case "1":
							it.INFO.@KEY ="03B"
							break
						case "2":
							it.INFO.@KEY ="10B"
							break
						case "3":
							it.INFO.@KEY ="05B"
							break
						case "4":
							it.INFO.@KEY ="12B"
							break
						case "5":
							it.INFO.@KEY ="07B"
							break
						case "6":
							it.INFO.@KEY ="02B"
							break
						case "7":
							it.INFO.@KEY ="09B"
							break
						case "8":
							it.INFO.@KEY ="04B"
							break
						case "9":
							it.INFO.@KEY ="11B"
							break
						case "10":
							it.INFO.@KEY ="06B"
							break
						case "11":
							it.INFO.@KEY ="01B"
							break
						case "12":
							it.INFO.@KEY ="05A"
							break
						case "13":
							it.INFO.@KEY ="12A"
							break
						case "14":
							it.INFO.@KEY ="07A"
							break
						case "15":
							it.INFO.@KEY ="02A"
							break
						case "16":
							it.INFO.@KEY ="09A"
							break
						case "17":
							it.INFO.@KEY ="04A"
							break
						case "18":
							it.INFO.@KEY ="11A"
							break
						case "19":
							it.INFO.@KEY ="06A"
							break
						case "20":
							it.INFO.@KEY ="01A"
							break
						case "21":
							it.INFO.@KEY ="08A"
							break
						case "22":
							it.INFO.@KEY ="03A"
							break
						case "23":
							it.INFO.@KEY ="10A"
							break
						default:
							println "something went wrong!"
							println it.MUSICAL_KEY[0].@VALUE
							break
					}
				}
				else
				{
					counter++
				}
			}
			
			new File("collection_out.nml").withWriter('UTF-8') { out ->
			    out << new StreamingMarkupBuilder().bind { mkp.pi( xml:[ version:'1.0', encoding: 'UTF-8', standalone:'no' ] ) }
			    new XmlNodePrinter(new PrintWriter(out)).print(xml)
			}
			println counter + " entries need to be analyzed and could not be parsed."
			println "New collection file is named collection_out.nml"
			println "Back up your old collection file somewhere, just in case, then rename the new file. Traktor should load it."
			println "Job done."
		}
		else
		{
			println "No collection file detected."
		}
	}
}