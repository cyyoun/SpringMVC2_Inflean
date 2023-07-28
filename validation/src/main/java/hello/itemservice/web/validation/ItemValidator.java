package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    // 어떤 타입의 객체를 검증할 때 이 객체의 클래스가 Validator가 검증할 수 있는지 판단하는 메서드
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz); //Item 클래스 및 자식 클래스까지 포함하는지 확인
    }
    @Override
    // 실제 검증 로직이 이루어지는 메서드
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        //검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName","required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }


    }
}
