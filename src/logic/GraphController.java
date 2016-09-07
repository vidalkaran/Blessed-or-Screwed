package logic;

import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

//THIS CLASS WITH HANDLE THE GRAPH

public class GraphController 
{
	
JFreeChart xylineChart;
ChartPanel chartPanel;
XYPlot plot;

//SINGLETON
private static GraphController instance = null;	// DataStorage singleton instance	

public static GraphController getInstance() {
	if(instance == null) {
		instance = new GraphController();
	}
	return instance;
}
	
 public XYSeriesCollection createDataset( )
 {
    XYSeriesCollection dataset = new XYSeriesCollection( );          

    return dataset;
 }

 public XYSeriesCollection createDataset(String hello )
 {      
    final XYSeries localDataset = new XYSeries("localDataset");          
    localDataset.add( 1.0 , 10.0 );          
    localDataset.add( 2.0 , 22.0 );          
    localDataset.add( 3.0 , 5.0 );        
    
    final XYSeries InputDataset = new XYSeries("InputDataset");          
    InputDataset.add( 1.0 , 5.0 );          
    InputDataset.add( 2.0 , 30.0 );          
    InputDataset.add( 3.0 , 12.0 );        
    
    XYSeriesCollection dataset = new XYSeriesCollection( );                   
    dataset.addSeries(localDataset);
    dataset.addSeries(InputDataset);

    return dataset;
 }
 
 public void createGraph( String applicationTitle)
 {
    xylineChart = ChartFactory.createXYLineChart(
    	applicationTitle ,
       "Level" ,
       "Stat" ,
       createDataset( ),
       PlotOrientation.VERTICAL ,
       false , false , false);
       
    chartPanel = new ChartPanel( xylineChart );
    chartPanel.setPreferredSize( new java.awt.Dimension( 400 , 200) );
    plot = xylineChart.getXYPlot( );
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
    renderer.setSeriesPaint( 0 , Color.RED );
    renderer.setSeriesPaint( 1 , Color.GREEN );
    renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
    renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
    plot.setRenderer( renderer ); 
}
 
 public void setDataset(XYSeriesCollection dataset)
 {
	 plot.setDataset(dataset);
 }

 public ChartPanel getChartPanel()
 {
	 return chartPanel;
 }
 
 public JFreeChart getChart()
 {
	 return xylineChart;
 }
}
