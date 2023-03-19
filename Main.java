package homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		// Toplam Satır Sayısı -->50
		// Toplam Kelime Sayısı --> 273
		// Toplam Karakter Sayısı --> 1593
		// En çok tekrar eden kelime --> arkadaş

		int satirSayisi = 0;
		int kelimeSayisi = 0;
		int karakterSayisi = 0;
		
		
		Map<String, Integer> kelimeTekrari = new HashMap<>();
		// dosyayı okumak için BufferedReader nesnesi oluşturduk
		try (BufferedReader reader = new BufferedReader(new FileReader("mars.txt"))) {
			//while döngüsüyle dosya içindeki metin satır satır okunuyor
			
			String satir = reader.readLine();

			while (satir != null) {

				satirSayisi++;
				//büyük küçük harf farkı yüzünden aynı kelimeyi iki farklı kelime gibi algılamaması için
				//okunan satırları küçük harflere dönüştürdük ve 
				//string dizisi kullanarak satırlardaki kelimeleri dizi elemanlarına ayırdık
				String[] satirdakiKelimelerinDizisi = satir.toLowerCase().split(" ");
				kelimeSayisi += satirdakiKelimelerinDizisi.length;
				
				
				 // kelime sayısını bulmak ve kelime tekrarlarını hesaplamak için dizideki elemanları geziyoruz: 
				for (String kelime : satirdakiKelimelerinDizisi) {
					 // karakter uzunluğunu karakterSayisi değişkenine ekliyoruz
		             karakterSayisi += kelime.length();  
		            
					//kelime sonunda özel karakter var mı diye kontrol ediyoruz,
					//çünkü Arkadaş! kelimesini ünlem var diye saymadı.
					//kelimenin sonunda özel karakter varsa son karakteri çıkarıp kelimeyi yeniden tanımlıyoruz.
					if (kelime.length() > 0) {  //kelime.length>0 'ı kontrol etmediğimizde -1 döndürdüğünden hata veriyor.
						char sonKarakter = kelime.charAt(kelime.length() - 1);
					    if (sonKarakter == '.' || sonKarakter == ',' || sonKarakter == '?' || sonKarakter == '!' || sonKarakter == ';'
					            || sonKarakter == ':') {
					        kelime = kelime.substring(0, kelime.length() - 1); // Son karakteri çıkararak kelimeyi yeniden tanımladık
					    }
					}
				    // tekrar eden kelime için: 
					if (kelimeTekrari.containsKey(kelime)) {                      // Eğer kelime zaten varsa,
						kelimeTekrari.put(kelime, kelimeTekrari.get(kelime) + 1); // yeniden ekleyip kelimenin tekrar sayısını 1 artırıyoruz. 
					} else {
						kelimeTekrari.put(kelime, 1);  // yoksa, kelimeyi ekleyip sayısını 1 yapıyoruz.
					}
				}
				satir = reader.readLine();
			}

			System.out.println("Toplam Satir Sayisi: " + satirSayisi);
			System.out.println("Toplam Kelime Sayisi: " + kelimeSayisi);
			System.out.println("Toplam Karakter Sayisi: " + karakterSayisi);

			// en çok tekrar eden kelime ve tekrar sayısı:
			int tekrarSayisi = 0;
			String enCokTekrarEdenKelime = null;

			for (Map.Entry entry : kelimeTekrari.entrySet()) {
				// kelimenin tekrar sayısının, daha önce bulunan maks tekrar sayısından büyük olup olmadığına bakıyoruz
				if (Integer.parseInt(entry.getValue().toString()) > tekrarSayisi && !entry.getKey().equals("")) {
					enCokTekrarEdenKelime = entry.getKey().toString();
					tekrarSayisi = (int) entry.getValue();
				}
			}

			System.out.println("\"" + enCokTekrarEdenKelime + "\"" + " kelimesi " + tekrarSayisi + " defa tekrar ediyor.");

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
