/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 海事业务月度通报Entity
 * @author Dylan Wang
 * @version 2017-07-24
 */
public class ReportMonthly extends DataEntity<ReportMonthly> {
	
	private static final long serialVersionUID = 1L;
	private Integer cruise;		// 巡航（艘次）
	private Double cruiseTotalTime;		// 巡航时间（小时）
	private Integer cruiseTotalDistance;		// 巡航里程（海里）
	private Integer cruisePeopleCount;		// 出动执法人员（人次）
	private Integer nightCruiseCount;		// 夜航（次）
	private Integer illegalCount;		// 海巡艇发现违章（艘次）
	private Integer dealIllegalCount;		// 海巡艇发现违章处理起数
	private Integer cruiseCar;		// 出动车辆（台次）
	private Integer luyuPeopleCount;		// 出动执法人员（人次）
	private Integer shipCheck;		// 检查船舶（艘次）
	private Integer luyuShipCheck;		// 检查船舶（艘次）
	private Integer luyuIllegalCount;		// 发现违章（艘次）
	private Integer luyuDealCount;		// 发现违章处理起数
	private Integer vtsShipReport;		// 接受船舶报告（艘次）
	private Integer vtsShipMonitor;		// 实施船舶跟踪（艘次）
	private Integer vtsIllegal;		// 纠正违章（艘次）
	private Integer vtsTraffic;		// 交通组织（艘次）
	private Integer vtsInfo;		// 提供信息服务（艘次）
	private Integer vtsUnion;		// 支持联合行动（次）
	private Integer vtsMonitor;		// 重点监控船舶
	private Integer dangerEmergencyCount;		// 突发事件起数（个）
	private Integer dangerCount;		// 险情起数（个）
	private Integer dangerPeople;		// 遇险人数（人）
	private Integer rescuePeople;		// 获救人数（人）
	private Integer dangerShip;		// 遇险船舶（艘）
	private Integer rescueShip;		// 救助船舶（艘）
	private Integer upDangerCount;		// 险情起数（个）
	private Integer upDangerPeople;		// 遇险人数（人）
	private Integer upRescuePeople;		// 获救人数（人）
	private Integer upDangerShip;		// 遇险船舶（艘）
	private Integer upRescueShip;		// 救助船舶（艘）
	private Integer shipOut;		// 出动海巡艇（艘次）
	private Integer socialShipOut;		// 出动社会船艇（艘次）
	private Double savePercent;		// 人命救助成功率（%）
	private Integer accidentCount;		// 事故件数（个）
	private Integer tantCount;		// 沉船（艘）
	private Integer economicLoss;		// 经济损失（万元）
	private Integer levelAccidentCount;		// 事故件数
	private Integer levelTantCount;		// 沉船（艘）
	private Integer levelEconomicLost;		// 经济损失（万元）
	private Integer projectAudit;		// 水工审批（件）
	private Integer projectPreview;		// 通航报备（件）
	private Integer projectNow;		// 在建水工（件）
	private Integer navigatorNotice;		// 航行通告（件）
	private Integer advisory;		// 发布灾害性天气预警信息（期）
	private Integer tempTrafficControl;		// 临时交通管制次数
	private Double controlHours;		// 实施时间（小时）
	private Integer controlDanger;		// 水工作业/发生险情次数
	private Double controlDangerHours;		// 实施时间（小时）
	private Integer shipInPort;		// 到港（艘次）
	private Integer shipByPort;		// 过境（艘次）
	private Integer hiddenDanger;		// 辖区安全隐患（个）
	private Integer hiddenDangerReport;		// 发放隐患整改通知书（份）
	private Integer reformHiddenDanger;		// 已整改隐患（个）
	private Integer overWeight;		// 发现超载（艘次）
	private Integer penaltyOverWeight;		// 处罚超载（艘次）
	private Double penaltyOverWeightMoney;		// 处罚超载金额（万元）
	private Integer shipReport;		// 船舶签证（不含定期签证）
	private Integer shipReportAll;		// 船舶签证（总艘次）
	private Double seaShipReport;		// 海船电子签证率
	private Integer internalAudit;		// 进口岸审批（艘次）
	private Integer internalSingleCount;		// 单船艘次（单）
	private Integer intralShipCount;		// 进出港船舶总艘次
	private Integer shipRegist;		// 船舶登记
	private Double throughputInPort;		// 内贸（万吨）
	private Double throughputOutPort;		// 外贸（万吨）
	private Double throughputTotalPort;		// 总计（万吨）
	private Double throughputInWharf;		// 内贸（万吨）
	private Double throughputOutWharf;		// 外贸（万吨）
	private Double throughputTotalWharf;		// 总计（万吨）
	private Double shipBurden;		// 船载货量（万吨）
	private Integer pscCount;		// psc艘次
	private Integer pscDefect;		// psc缺陷数
	private Integer pscRetention;		// psc滞留数
	private Integer seaCount;		// sea艘次
	private Integer seaDefect;		// sea缺陷数
	private Integer seaRetention;		// sea滞留数
	private Integer riverboatCount;		// riverboat艘次
	private Integer riverboatDefect;		// riverboat缺陷数
	private Integer riverboatRetention;		// riverboat滞留数
	private Integer portCount;		// 渡运数量（航次）
	private Double portPeopleCount;		// 渡运车辆（万车次）
	private Double portCarCount;		// 渡运人员（万人次）
	private Integer sanhuaCount;		// 散化申报
	private Integer baozhuangCount;		// 包装申报
	private Integer sanguCount;		// 散固申报
	private Integer aSanguCount;		// 其中A组散固
	private Integer dangerTotalCount;		// 危货船舶总计
	private Double sanhuaCargoCount;		// 散化
	private Double baozhuangCargoCount;		// 包装类
	private Double sanguCargoCount;		// 散固
	private Double aSanguCargoCount;		// 其中A组散固
	private Double dangerCargoTotalCount;		// 危货吞吐量总计
	private Double canyouCount;		// 残油、油泥、油污水接收量（吨）
	private Double xicangshuiCount;		// 洗舱水作业量（吨）
	private Double yazaishuiCount;		// 压载水排放量（吨）
	private Double fangwuzuoyeCount;		// 防污作业审批数（次）
	private Integer boxCheckCount;		// 检查箱数
	private Integer boxDefectCount;		// 缺陷数
	private Double boxDefectPercent;		// 缺陷率
	private Integer penaltyCount;		// 处罚件数
	private Double penaltyMoneyCount;		// 处罚金额（万元）
	private Integer penaltyNotice;		// 未处罚予教育放行
	private Double moneyZwzx;		// money政务中心
	private Double moneyGqc;		// money港区处
	private Double moneyBsq;		// money保税区处
	private Double moneyJfc;		// money锦丰处
	private Double moneyNfc;		// money南丰处
	private Double moneyDqb;		// money大桥办
	private Double moneyDaishou;		// money代收单位
	private Double moneyTotal;		// money合计
	
	public ReportMonthly() {
		super();
	}

	public ReportMonthly(String id){
		super(id);
	}

	public Integer getCruise() {
		return cruise;
	}

	public void setCruise(Integer cruise) {
		this.cruise = cruise;
	}
	
	public Double getCruiseTotalTime() {
		return cruiseTotalTime;
	}

	public void setCruiseTotalTime(Double cruiseTotalTime) {
		this.cruiseTotalTime = cruiseTotalTime;
	}
	
	public Integer getCruiseTotalDistance() {
		return cruiseTotalDistance;
	}

	public void setCruiseTotalDistance(Integer cruiseTotalDistance) {
		this.cruiseTotalDistance = cruiseTotalDistance;
	}
	
	public Integer getCruisePeopleCount() {
		return cruisePeopleCount;
	}

	public void setCruisePeopleCount(Integer cruisePeopleCount) {
		this.cruisePeopleCount = cruisePeopleCount;
	}
	
	public Integer getNightCruiseCount() {
		return nightCruiseCount;
	}

	public void setNightCruiseCount(Integer nightCruiseCount) {
		this.nightCruiseCount = nightCruiseCount;
	}
	
	public Integer getIllegalCount() {
		return illegalCount;
	}

	public void setIllegalCount(Integer illegalCount) {
		this.illegalCount = illegalCount;
	}
	
	public Integer getDealIllegalCount() {
		return dealIllegalCount;
	}

	public void setDealIllegalCount(Integer dealIllegalCount) {
		this.dealIllegalCount = dealIllegalCount;
	}
	
	public Integer getCruiseCar() {
		return cruiseCar;
	}

	public void setCruiseCar(Integer cruiseCar) {
		this.cruiseCar = cruiseCar;
	}
	
	public Integer getLuyuPeopleCount() {
		return luyuPeopleCount;
	}

	public void setLuyuPeopleCount(Integer luyuPeopleCount) {
		this.luyuPeopleCount = luyuPeopleCount;
	}
	
	public Integer getShipCheck() {
		return shipCheck;
	}

	public void setShipCheck(Integer shipCheck) {
		this.shipCheck = shipCheck;
	}
	
	public Integer getLuyuShipCheck() {
		return luyuShipCheck;
	}

	public void setLuyuShipCheck(Integer luyuShipCheck) {
		this.luyuShipCheck = luyuShipCheck;
	}
	
	public Integer getLuyuIllegalCount() {
		return luyuIllegalCount;
	}

	public void setLuyuIllegalCount(Integer luyuIllegalCount) {
		this.luyuIllegalCount = luyuIllegalCount;
	}
	
	public Integer getLuyuDealCount() {
		return luyuDealCount;
	}

	public void setLuyuDealCount(Integer luyuDealCount) {
		this.luyuDealCount = luyuDealCount;
	}
	
	public Integer getVtsShipReport() {
		return vtsShipReport;
	}

	public void setVtsShipReport(Integer vtsShipReport) {
		this.vtsShipReport = vtsShipReport;
	}
	
	public Integer getVtsShipMonitor() {
		return vtsShipMonitor;
	}

	public void setVtsShipMonitor(Integer vtsShipMonitor) {
		this.vtsShipMonitor = vtsShipMonitor;
	}
	
	public Integer getVtsIllegal() {
		return vtsIllegal;
	}

	public void setVtsIllegal(Integer vtsIllegal) {
		this.vtsIllegal = vtsIllegal;
	}
	
	public Integer getVtsTraffic() {
		return vtsTraffic;
	}

	public void setVtsTraffic(Integer vtsTraffic) {
		this.vtsTraffic = vtsTraffic;
	}
	
	public Integer getVtsInfo() {
		return vtsInfo;
	}

	public void setVtsInfo(Integer vtsInfo) {
		this.vtsInfo = vtsInfo;
	}
	
	public Integer getVtsUnion() {
		return vtsUnion;
	}

	public void setVtsUnion(Integer vtsUnion) {
		this.vtsUnion = vtsUnion;
	}
	
	public Integer getVtsMonitor() {
		return vtsMonitor;
	}

	public void setVtsMonitor(Integer vtsMonitor) {
		this.vtsMonitor = vtsMonitor;
	}
	
	public Integer getDangerEmergencyCount() {
		return dangerEmergencyCount;
	}

	public void setDangerEmergencyCount(Integer dangerEmergencyCount) {
		this.dangerEmergencyCount = dangerEmergencyCount;
	}
	
	public Integer getDangerCount() {
		return dangerCount;
	}

	public void setDangerCount(Integer dangerCount) {
		this.dangerCount = dangerCount;
	}
	
	public Integer getDangerPeople() {
		return dangerPeople;
	}

	public void setDangerPeople(Integer dangerPeople) {
		this.dangerPeople = dangerPeople;
	}
	
	public Integer getRescuePeople() {
		return rescuePeople;
	}

	public void setRescuePeople(Integer rescuePeople) {
		this.rescuePeople = rescuePeople;
	}
	
	public Integer getDangerShip() {
		return dangerShip;
	}

	public void setDangerShip(Integer dangerShip) {
		this.dangerShip = dangerShip;
	}
	
	public Integer getRescueShip() {
		return rescueShip;
	}

	public void setRescueShip(Integer rescueShip) {
		this.rescueShip = rescueShip;
	}
	
	public Integer getUpDangerCount() {
		return upDangerCount;
	}

	public void setUpDangerCount(Integer upDangerCount) {
		this.upDangerCount = upDangerCount;
	}
	
	public Integer getUpDangerPeople() {
		return upDangerPeople;
	}

	public void setUpDangerPeople(Integer upDangerPeople) {
		this.upDangerPeople = upDangerPeople;
	}
	
	public Integer getUpRescuePeople() {
		return upRescuePeople;
	}

	public void setUpRescuePeople(Integer upRescuePeople) {
		this.upRescuePeople = upRescuePeople;
	}
	
	public Integer getUpDangerShip() {
		return upDangerShip;
	}

	public void setUpDangerShip(Integer upDangerShip) {
		this.upDangerShip = upDangerShip;
	}
	
	public Integer getUpRescueShip() {
		return upRescueShip;
	}

	public void setUpRescueShip(Integer upRescueShip) {
		this.upRescueShip = upRescueShip;
	}
	
	public Integer getShipOut() {
		return shipOut;
	}

	public void setShipOut(Integer shipOut) {
		this.shipOut = shipOut;
	}
	
	public Integer getSocialShipOut() {
		return socialShipOut;
	}

	public void setSocialShipOut(Integer socialShipOut) {
		this.socialShipOut = socialShipOut;
	}
	
	public Double getSavePercent() {
		return savePercent;
	}

	public void setSavePercent(Double savePercent) {
		this.savePercent = savePercent;
	}
	
	public Integer getAccidentCount() {
		return accidentCount;
	}

	public void setAccidentCount(Integer accidentCount) {
		this.accidentCount = accidentCount;
	}
	
	public Integer getTantCount() {
		return tantCount;
	}

	public void setTantCount(Integer tantCount) {
		this.tantCount = tantCount;
	}
	
	public Integer getEconomicLoss() {
		return economicLoss;
	}

	public void setEconomicLoss(Integer economicLoss) {
		this.economicLoss = economicLoss;
	}
	
	public Integer getLevelAccidentCount() {
		return levelAccidentCount;
	}

	public void setLevelAccidentCount(Integer levelAccidentCount) {
		this.levelAccidentCount = levelAccidentCount;
	}
	
	public Integer getLevelTantCount() {
		return levelTantCount;
	}

	public void setLevelTantCount(Integer levelTantCount) {
		this.levelTantCount = levelTantCount;
	}
	
	public Integer getLevelEconomicLost() {
		return levelEconomicLost;
	}

	public void setLevelEconomicLost(Integer levelEconomicLost) {
		this.levelEconomicLost = levelEconomicLost;
	}
	
	public Integer getProjectAudit() {
		return projectAudit;
	}

	public void setProjectAudit(Integer projectAudit) {
		this.projectAudit = projectAudit;
	}
	
	public Integer getProjectPreview() {
		return projectPreview;
	}

	public void setProjectPreview(Integer projectPreview) {
		this.projectPreview = projectPreview;
	}
	
	public Integer getProjectNow() {
		return projectNow;
	}

	public void setProjectNow(Integer projectNow) {
		this.projectNow = projectNow;
	}
	
	public Integer getNavigatorNotice() {
		return navigatorNotice;
	}

	public void setNavigatorNotice(Integer navigatorNotice) {
		this.navigatorNotice = navigatorNotice;
	}
	
	public Integer getAdvisory() {
		return advisory;
	}

	public void setAdvisory(Integer advisory) {
		this.advisory = advisory;
	}
	
	public Integer getTempTrafficControl() {
		return tempTrafficControl;
	}

	public void setTempTrafficControl(Integer tempTrafficControl) {
		this.tempTrafficControl = tempTrafficControl;
	}
	
	public Double getControlHours() {
		return controlHours;
	}

	public void setControlHours(Double controlHours) {
		this.controlHours = controlHours;
	}
	
	public Integer getControlDanger() {
		return controlDanger;
	}

	public void setControlDanger(Integer controlDanger) {
		this.controlDanger = controlDanger;
	}
	
	public Double getControlDangerHours() {
		return controlDangerHours;
	}

	public void setControlDangerHours(Double controlDangerHours) {
		this.controlDangerHours = controlDangerHours;
	}
	
	public Integer getShipInPort() {
		return shipInPort;
	}

	public void setShipInPort(Integer shipInPort) {
		this.shipInPort = shipInPort;
	}
	
	public Integer getShipByPort() {
		return shipByPort;
	}

	public void setShipByPort(Integer shipByPort) {
		this.shipByPort = shipByPort;
	}
	
	public Integer getHiddenDanger() {
		return hiddenDanger;
	}

	public void setHiddenDanger(Integer hiddenDanger) {
		this.hiddenDanger = hiddenDanger;
	}
	
	public Integer getHiddenDangerReport() {
		return hiddenDangerReport;
	}

	public void setHiddenDangerReport(Integer hiddenDangerReport) {
		this.hiddenDangerReport = hiddenDangerReport;
	}
	
	public Integer getReformHiddenDanger() {
		return reformHiddenDanger;
	}

	public void setReformHiddenDanger(Integer reformHiddenDanger) {
		this.reformHiddenDanger = reformHiddenDanger;
	}
	
	public Integer getOverWeight() {
		return overWeight;
	}

	public void setOverWeight(Integer overWeight) {
		this.overWeight = overWeight;
	}
	
	public Integer getPenaltyOverWeight() {
		return penaltyOverWeight;
	}

	public void setPenaltyOverWeight(Integer penaltyOverWeight) {
		this.penaltyOverWeight = penaltyOverWeight;
	}
	
	public Double getPenaltyOverWeightMoney() {
		return penaltyOverWeightMoney;
	}

	public void setPenaltyOverWeightMoney(Double penaltyOverWeightMoney) {
		this.penaltyOverWeightMoney = penaltyOverWeightMoney;
	}
	
	public Integer getShipReport() {
		return shipReport;
	}

	public void setShipReport(Integer shipReport) {
		this.shipReport = shipReport;
	}
	
	public Integer getShipReportAll() {
		return shipReportAll;
	}

	public void setShipReportAll(Integer shipReportAll) {
		this.shipReportAll = shipReportAll;
	}
	
	public Double getSeaShipReport() {
		return seaShipReport;
	}

	public void setSeaShipReport(Double seaShipReport) {
		this.seaShipReport = seaShipReport;
	}
	
	public Integer getInternalAudit() {
		return internalAudit;
	}

	public void setInternalAudit(Integer internalAudit) {
		this.internalAudit = internalAudit;
	}
	
	public Integer getInternalSingleCount() {
		return internalSingleCount;
	}

	public void setInternalSingleCount(Integer internalSingleCount) {
		this.internalSingleCount = internalSingleCount;
	}
	
	public Integer getIntralShipCount() {
		return intralShipCount;
	}

	public void setIntralShipCount(Integer intralShipCount) {
		this.intralShipCount = intralShipCount;
	}
	
	public Integer getShipRegist() {
		return shipRegist;
	}

	public void setShipRegist(Integer shipRegist) {
		this.shipRegist = shipRegist;
	}
	
	public Double getThroughputInPort() {
		return throughputInPort;
	}

	public void setThroughputInPort(Double throughputInPort) {
		this.throughputInPort = throughputInPort;
	}
	
	public Double getThroughputOutPort() {
		return throughputOutPort;
	}

	public void setThroughputOutPort(Double throughputOutPort) {
		this.throughputOutPort = throughputOutPort;
	}
	
	public Double getThroughputTotalPort() {
		return throughputTotalPort;
	}

	public void setThroughputTotalPort(Double throughputTotalPort) {
		this.throughputTotalPort = throughputTotalPort;
	}
	
	public Double getThroughputInWharf() {
		return throughputInWharf;
	}

	public void setThroughputInWharf(Double throughputInWharf) {
		this.throughputInWharf = throughputInWharf;
	}
	
	public Double getThroughputOutWharf() {
		return throughputOutWharf;
	}

	public void setThroughputOutWharf(Double throughputOutWharf) {
		this.throughputOutWharf = throughputOutWharf;
	}
	
	public Double getThroughputTotalWharf() {
		return throughputTotalWharf;
	}

	public void setThroughputTotalWharf(Double throughputTotalWharf) {
		this.throughputTotalWharf = throughputTotalWharf;
	}
	
	public Double getShipBurden() {
		return shipBurden;
	}

	public void setShipBurden(Double shipBurden) {
		this.shipBurden = shipBurden;
	}
	
	public Integer getPscCount() {
		return pscCount;
	}

	public void setPscCount(Integer pscCount) {
		this.pscCount = pscCount;
	}
	
	public Integer getPscDefect() {
		return pscDefect;
	}

	public void setPscDefect(Integer pscDefect) {
		this.pscDefect = pscDefect;
	}
	
	public Integer getPscRetention() {
		return pscRetention;
	}

	public void setPscRetention(Integer pscRetention) {
		this.pscRetention = pscRetention;
	}
	
	public Integer getSeaCount() {
		return seaCount;
	}

	public void setSeaCount(Integer seaCount) {
		this.seaCount = seaCount;
	}
	
	public Integer getSeaDefect() {
		return seaDefect;
	}

	public void setSeaDefect(Integer seaDefect) {
		this.seaDefect = seaDefect;
	}
	
	public Integer getSeaRetention() {
		return seaRetention;
	}

	public void setSeaRetention(Integer seaRetention) {
		this.seaRetention = seaRetention;
	}
	
	public Integer getRiverboatCount() {
		return riverboatCount;
	}

	public void setRiverboatCount(Integer riverboatCount) {
		this.riverboatCount = riverboatCount;
	}
	
	public Integer getRiverboatDefect() {
		return riverboatDefect;
	}

	public void setRiverboatDefect(Integer riverboatDefect) {
		this.riverboatDefect = riverboatDefect;
	}
	
	public Integer getRiverboatRetention() {
		return riverboatRetention;
	}

	public void setRiverboatRetention(Integer riverboatRetention) {
		this.riverboatRetention = riverboatRetention;
	}
	
	public Integer getPortCount() {
		return portCount;
	}

	public void setPortCount(Integer portCount) {
		this.portCount = portCount;
	}
	
	public Double getPortPeopleCount() {
		return portPeopleCount;
	}

	public void setPortPeopleCount(Double portPeopleCount) {
		this.portPeopleCount = portPeopleCount;
	}
	
	public Double getPortCarCount() {
		return portCarCount;
	}

	public void setPortCarCount(Double portCarCount) {
		this.portCarCount = portCarCount;
	}
	
	public Integer getSanhuaCount() {
		return sanhuaCount;
	}

	public void setSanhuaCount(Integer sanhuaCount) {
		this.sanhuaCount = sanhuaCount;
	}
	
	public Integer getBaozhuangCount() {
		return baozhuangCount;
	}

	public void setBaozhuangCount(Integer baozhuangCount) {
		this.baozhuangCount = baozhuangCount;
	}
	
	public Integer getSanguCount() {
		return sanguCount;
	}

	public void setSanguCount(Integer sanguCount) {
		this.sanguCount = sanguCount;
	}
	
	public Integer getASanguCount() {
		return aSanguCount;
	}

	public void setASanguCount(Integer aSanguCount) {
		this.aSanguCount = aSanguCount;
	}
	
	public Integer getDangerTotalCount() {
		return dangerTotalCount;
	}

	public void setDangerTotalCount(Integer dangerTotalCount) {
		this.dangerTotalCount = dangerTotalCount;
	}
	
	public Double getSanhuaCargoCount() {
		return sanhuaCargoCount;
	}

	public void setSanhuaCargoCount(Double sanhuaCargoCount) {
		this.sanhuaCargoCount = sanhuaCargoCount;
	}
	
	public Double getBaozhuangCargoCount() {
		return baozhuangCargoCount;
	}

	public void setBaozhuangCargoCount(Double baozhuangCargoCount) {
		this.baozhuangCargoCount = baozhuangCargoCount;
	}
	
	public Double getSanguCargoCount() {
		return sanguCargoCount;
	}

	public void setSanguCargoCount(Double sanguCargoCount) {
		this.sanguCargoCount = sanguCargoCount;
	}
	
	public Double getASanguCargoCount() {
		return aSanguCargoCount;
	}

	public void setASanguCargoCount(Double aSanguCargoCount) {
		this.aSanguCargoCount = aSanguCargoCount;
	}
	
	public Double getDangerCargoTotalCount() {
		return dangerCargoTotalCount;
	}

	public void setDangerCargoTotalCount(Double dangerCargoTotalCount) {
		this.dangerCargoTotalCount = dangerCargoTotalCount;
	}
	
	public Double getCanyouCount() {
		return canyouCount;
	}

	public void setCanyouCount(Double canyouCount) {
		this.canyouCount = canyouCount;
	}
	
	public Double getXicangshuiCount() {
		return xicangshuiCount;
	}

	public void setXicangshuiCount(Double xicangshuiCount) {
		this.xicangshuiCount = xicangshuiCount;
	}
	
	public Double getYazaishuiCount() {
		return yazaishuiCount;
	}

	public void setYazaishuiCount(Double yazaishuiCount) {
		this.yazaishuiCount = yazaishuiCount;
	}
	
	public Double getFangwuzuoyeCount() {
		return fangwuzuoyeCount;
	}

	public void setFangwuzuoyeCount(Double fangwuzuoyeCount) {
		this.fangwuzuoyeCount = fangwuzuoyeCount;
	}
	
	public Integer getBoxCheckCount() {
		return boxCheckCount;
	}

	public void setBoxCheckCount(Integer boxCheckCount) {
		this.boxCheckCount = boxCheckCount;
	}
	
	public Integer getBoxDefectCount() {
		return boxDefectCount;
	}

	public void setBoxDefectCount(Integer boxDefectCount) {
		this.boxDefectCount = boxDefectCount;
	}
	
	public Double getBoxDefectPercent() {
		return boxDefectPercent;
	}

	public void setBoxDefectPercent(Double boxDefectPercent) {
		this.boxDefectPercent = boxDefectPercent;
	}
	
	public Integer getPenaltyCount() {
		return penaltyCount;
	}

	public void setPenaltyCount(Integer penaltyCount) {
		this.penaltyCount = penaltyCount;
	}
	
	public Double getPenaltyMoneyCount() {
		return penaltyMoneyCount;
	}

	public void setPenaltyMoneyCount(Double penaltyMoneyCount) {
		this.penaltyMoneyCount = penaltyMoneyCount;
	}
	
	public Integer getPenaltyNotice() {
		return penaltyNotice;
	}

	public void setPenaltyNotice(Integer penaltyNotice) {
		this.penaltyNotice = penaltyNotice;
	}
	
	public Double getMoneyZwzx() {
		return moneyZwzx;
	}

	public void setMoneyZwzx(Double moneyZwzx) {
		this.moneyZwzx = moneyZwzx;
	}
	
	public Double getMoneyGqc() {
		return moneyGqc;
	}

	public void setMoneyGqc(Double moneyGqc) {
		this.moneyGqc = moneyGqc;
	}
	
	public Double getMoneyBsq() {
		return moneyBsq;
	}

	public void setMoneyBsq(Double moneyBsq) {
		this.moneyBsq = moneyBsq;
	}
	
	public Double getMoneyJfc() {
		return moneyJfc;
	}

	public void setMoneyJfc(Double moneyJfc) {
		this.moneyJfc = moneyJfc;
	}
	
	public Double getMoneyNfc() {
		return moneyNfc;
	}

	public void setMoneyNfc(Double moneyNfc) {
		this.moneyNfc = moneyNfc;
	}
	
	public Double getMoneyDqb() {
		return moneyDqb;
	}

	public void setMoneyDqb(Double moneyDqb) {
		this.moneyDqb = moneyDqb;
	}
	
	public Double getMoneyDaishou() {
		return moneyDaishou;
	}

	public void setMoneyDaishou(Double moneyDaishou) {
		this.moneyDaishou = moneyDaishou;
	}
	
	public Double getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	
}