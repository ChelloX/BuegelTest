package org.woped.bpel.datamodel;

import org.woped.core.model.petrinet.TransitionModel;


public class Transition extends NonterminalElement<TransitionModel>
{

	public Transition(TransitionModel data)
	{
		super(data);
	}

	@Override
	public boolean accept_post_object(AbstractElement e)
	{
		if(Place.class.isInstance(e))return true;
		return false;
	}

	@Override
	public boolean accept_pre_object(AbstractElement e)
	{
		if(Place.class.isInstance(e))return true;
		return false;
	}

	@Override
	public boolean equals(AbstractElement e)
	{
		if(!Transition.class.isInstance(e))return false;
		if(((Transition)e).getData().getId() != this.getData().getId())return false;
		return true;
	}

	@Override
	public String getBpelCode()
	{
		return null;
	}

}