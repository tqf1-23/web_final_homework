
  describe('get todo', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:3000/');
    });
    it('should get dodo correctly',async function(){
      // 等待元素加载完成，再进行操作
      await page.waitFor(".item");
      let list = await page.$$(".item")
      console.log(list.length)
       expect(list).to.have.property('length');
     })
    after (async function () {
      await page.close();
    });
  });

  describe('add todo', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:3000/');
    });
  
    after (async function () {
      await page.close();
    });
    it('should new todo correct', async function() {
      await page.waitFor(".add-input");
      await page.type('.add-input', 'new todo item', {delay: 50});
      await page.click('.add-button',  {delay: 500});
      // 等待页面刷新
      await page.waitForResponse(res => {
        return res.request().url() &&
        res.ok()
    })
      let todoList = await page.waitFor('#todoList');
      const expectInputContent = await page.evaluate(todoList => todoList.lastChild.querySelector('label').textContent, todoList);
      console.log(expectInputContent);
      expect(expectInputContent).to.eql('new todo item');
    }) 

  });

    describe('delete todo', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:3000/');
    });
   it('should delete dodo correctly',async function(){
      await page.waitFor(".item");
      let length = await page.$$eval(".item",list=>list.length);
      console.log(length)
     await page.click('.delete-button',  {delay: 500});
      await page.waitForResponse(res => {
        return res.request().url() &&
        res.ok()
    })
    let length2 = await page.$$eval(".item",list=>list.length);
   expect(length).to.eql(length2+1);
   })
    after (async function () {
      await page.close();
    });
  });

  describe('update todo', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:3000/');
    });
   it('should update dodo correctly',async function(){
      await page.waitFor(".item");
      await page.click('.update-input');
      await page.type('.update-input', 'new todo item(updated)', {delay: 50});
      await page.click('.update-button', {delay:50});
      await page.waitForResponse(res => {
        return res.request().url() &&
        res.ok()
    })
    let todoList = await page.waitFor('#todoList');
      const expectInputContent = await page.evaluate(todoList => todoList.firstChild.querySelector('label').textContent, todoList);
      expect(expectInputContent).to.eql('new todo item(updated)');

    })
    after (async function () {
      await page.close();
    });
  });