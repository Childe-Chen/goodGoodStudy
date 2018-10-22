售卖机整体可看作是[状态机](https://github.com/Childe-Chen/statemachine)的一个实例。每个模块可以看作状态机。

---------------------------------------------可爱的分割线---------------------------------------------
贩卖机由显示器等模块组成，模块间通过事件解除耦合。
贩卖机启动时，依次创建各个模块，模块（具备事件处理能力）在初始化时向注册中心注册关注的事件类型，模块产生的事件经由事件中心分发处理。


对事件的响应需要多个模块协作完成，需要保证操作的原子性，将对同一事件感兴趣的handler组成链表。

两个实现思路：

    1.考虑将同一事件的处理者按照权重排序。权重为数字0～99，权重相同的按照加载顺序依次排开。

    2.按照对现有业务的理解，可省略权重，简化处理，模块处理事件可乱序，只要保证事务操作即可（采纳，简单实现）。

操作失败处理思路：

    1.各个handler实现时采用copy-write的方式，操作拷贝数据，全部操作成功后写回。赋值操作失败几率小，此种实现较好滚回数据（采纳，未实现）。

商品有工厂模拟生产后放到货架。

货架抽象为一个二维数组。


显示器：Screen implements EventHandler,Module

支付,退币：Pay implements EventHandler,Module--pay()

		|CashPay

		|CodePay

钱包：Wallet implements EventHandler--add(),sub(),refund()

商品选择：CommditySelect implements Module

出货器：Shipper implements EventHandler,Module

事件：Event--getType(),getMsg()

		|PayEvent  支付事件: wallet增加 -> screen展示

		|RefundEvent 退币事件: wallet减少 -> screen展示

		|CommditySelectedEvent 商品选择事件: shelf确定商品 -> 产生ShipEvent

		|ShipEvent 商品选择后出货事件: wallet减少 -> 对应商品stock减少 -> screen展示

		|ContinuePayEvent 金额不足，继续支付事件: screen展示

		|NoStockEvent 库存不足事件: screen展示

		|NotFoundEvent 商品不存在事件: screen展示

事件枚举：EventEnum

事件中心：EventCenter--publish(),register(),distribution(),collectEventHandler()

事件处理：EventHandler--handle(),Event[] getInterestEvent(),commit()

贩卖机：SellMachine--init()

贩卖机模块：Module--on(),off()

货架：Shelf

商品：Commodity

饮料：Drink extends Commodity