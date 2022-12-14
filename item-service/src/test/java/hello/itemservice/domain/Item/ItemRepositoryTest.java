package hello.itemservice.domain.Item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item save = itemRepository.save(item);
        //then
        Item byId = itemRepository.findById(save.getId());

        assertThat(byId).isEqualTo(save);
    }

    @Test
    void findAll() {
        //given
        Item item = new Item("itemA",10000,10);
        Item item2 = new Item("itemB",20000,20);

        itemRepository.save(item);
        itemRepository.save(item2);
        //when

        List<Item> all = itemRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item,item2);
    }

    @Test
    void update() {
        //given
        Item item = new Item("itemA",10000,10);

        Item save = itemRepository.save(item);
        Long id = save.getId();

        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(id,updateParam);
        //then

        Item byId = itemRepository.findById(id);
        assertThat(byId.getItemName()).isEqualTo(item.getItemName());
        assertThat(byId.getPrice()).isEqualTo(item.getPrice());
    }

}