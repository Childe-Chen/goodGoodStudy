贩卖机由显示器等模块组成，模块间通过事件解除耦合。
贩卖机启动时，依次创建各个模块，模块（具备事件处理能力）在初始化时向注册中心注册关注的事件类型，模块产生的事件经由事件中心分发处理。


显示器：Screen implements EventHandler,Module

支付,退币：Pay implements EventHandler,Module--pay()
		|CashPay
		|CodePay

钱包：Wallet implements EventHandler--add(),sub(),refund()

商品选择：CommditySelect implements Module

出货器：Shipper implements EventHandler,Module

事件：Event--getType()

		|PayEvent  支付事件

		|RefundEvent 退币事件

		|CommditySelectedEvent 商品选择事件

		|ShipEvent 出货事件

		|ContinuePayEvent 金额不足，继续支付事件

事件枚举：EventEnum

事件中心：EventCenter--publish(),register(),distribution(),collectEventHandler()

事件处理：EventHandler--handle(),Event[] getInterestEvent()

贩卖机：SellMachine--init()

贩卖机模块：Module--on(),off()