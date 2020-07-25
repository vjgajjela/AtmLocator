package com.backbase.atm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.backbase.atm.model.Atm;
import com.backbase.atm.service.IAtmService;

public class AtmServiceMock implements IAtmService {

	@Override
	public List<Atm> getAtmsAsList() throws IOException {
		return null;
	}

	@Override
	public String getAtmsAsString() throws IOException {
		BufferedReader br = null;
		String response = "";
		try {
			br = new BufferedReader(
					new FileReader(System.getProperty("user.dir") + "/src/test/java/com/backbase/atm/TestResponse.txt"));
			while ((response = br.readLine()) != null) {
				break;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		System.out.println(response);
		return response.substring(response.indexOf(IConstants.JSON_ARRAY_ELEMENT));
	}

}
