package pe.com.tss.runakuna.rest;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.tss.runakuna.service.TestService;

/**
 * Created by josediaz on 10/10/2016.
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value="/test/get/json", method= RequestMethod.GET, produces="application/json")
    public String testGetJson() {
        JsonObject jsonObject = new JsonObject();
        JsonObject message = new JsonObject();

        String result = testService.test();

        message.addProperty("message", result);
        jsonObject.add("test", message);

        return jsonObject.toString();
    }
}
